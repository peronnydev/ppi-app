package br.edu.catolica.customer_ms.service;

import br.edu.catolica.customer_ms.domain.Customer;
import br.edu.catolica.customer_ms.dto.OrderCreatedEventDTO;
import br.edu.catolica.customer_ms.dto.OrderRequestDTO;
import br.edu.catolica.customer_ms.event.OrderEventPubliher;
import br.edu.catolica.customer_ms.exception.CustomerException;
import br.edu.catolica.customer_ms.repositories.CustomerRepository;
import br.edu.catolica.customer_ms.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventService {
    private final OrderServicePersistence orderServicePersistence;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderEventPubliher orderEventPubliher;

    public void send(OrderRequestDTO orderRequestDTO) {
        Customer customer = customerRepository
                .findById(orderRequestDTO.customerId())
                .orElseThrow(() -> {
                    log.warn("m=send, customer not found to id ={}", orderRequestDTO.customerId());
                    return new CustomerException("Customer não encontrado");
                });

        orderServicePersistence.saveOrder(withOrderCode(orderRequestDTO));

        OrderCreatedEventDTO eventDTO = OrderCreatedEventDTO.builder()
                .customerEmail(customer.getEmail())
                .customerId(customer.getId())
                .customerName(customer.getName())
                .items(orderRequestDTO.items())
                .orderCode(orderRequestDTO.orderCode())
                .sellerId(orderRequestDTO.sellerId())
                .build();
        try{
            orderEventPubliher.sendOrderRequestEvent(eventDTO);
        } catch (Exception e){
            log.error("m=send, fail try send order to topic, order code = {}", eventDTO.orderCode());
            orderServicePersistence.updateOrderToFailed(eventDTO.orderCode());
            throw new RuntimeException(e);
        }
    }

    private OrderRequestDTO withOrderCode(OrderRequestDTO orderRequestDTO){
        if(Strings.isNotBlank(orderRequestDTO.orderCode())){
            return orderRequestDTO;
        }
        return new OrderRequestDTO(
                orderRequestDTO.sellerId(),
                orderRequestDTO.customerId(),
                orderRequestDTO.items()
        );
    }
}
