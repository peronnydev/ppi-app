package br.edu.catolica.customer_ms.service;

import br.edu.catolica.customer_ms.cache.ProductCacheReader;
import br.edu.catolica.customer_ms.domain.ItemOrder;
import br.edu.catolica.customer_ms.domain.Order;
import br.edu.catolica.customer_ms.dto.ItemRequestDTO;
import br.edu.catolica.customer_ms.dto.OrderRequestDTO;
import br.edu.catolica.customer_ms.dto.ProductDTO;
import br.edu.catolica.customer_ms.enums.OrderStatus;
import br.edu.catolica.customer_ms.exception.ProductNotFoundException;
import br.edu.catolica.customer_ms.exception.SaveOrderException;
import br.edu.catolica.customer_ms.mapper.OrderMapper;
import br.edu.catolica.customer_ms.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServicePersistence {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductCacheReader productCacheReader;

    public void saveOrder(OrderRequestDTO orderRequestDTO){
        Map<Long, ProductDTO> products = productCacheReader.getProductsBySeller(orderRequestDTO.sellerId())
                .products()
                .stream()
                .collect(Collectors.toMap(ProductDTO::id, Function.identity()));

        Order order = orderMapper.dtoToEntity(orderRequestDTO);
        order.setStatus(OrderStatus.ANALYZE);

        List<ItemOrder> items = orderRequestDTO.items().stream()
                .map(item -> toItemOrder(item, products, order))
                .toList();
        order.setItems(items);
        order.setAmount(items.stream()
                .map(ItemOrder::getSubAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        try{
            orderRepository.save(order);
        } catch(Exception e){
            log.error("m=saveOrder, failed to try save order code = {}", orderRequestDTO.orderCode());
            throw new SaveOrderException(e.getMessage());
        }
    }

    public void updateOrderToFailed(String orderCode){
        orderRepository.findByOrderCode(orderCode)
                .ifPresentOrElse(order -> {
                    order.setStatus(OrderStatus.FAILED_TO_SEND);
                    orderRepository.save(order);
                }, () -> log.warn("m=updateOrderToFailed, order not found to order code = {}", orderCode));
    }

    private ItemOrder toItemOrder(ItemRequestDTO item, Map<Long, ProductDTO> products, Order order){
        ProductDTO product = products.get(item.productId());
        if (product == null) {
            log.warn("m=toItemOrder, product not found to id = {} and seller id = {}", item.productId(), order.getSellerId());
            throw new ProductNotFoundException("Produto " + item.productId() + " não encontrado para o vendedor");
        }
        return ItemOrder.builder()
                .productId(item.productId())
                .quantity(item.quantity())
                .subAmount(product.price().multiply(BigDecimal.valueOf(item.quantity())))
                .order(order)
                .build();
    }
}
