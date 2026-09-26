package br.edu.catolica.customer_ms.service;

import br.edu.catolica.customer_ms.domain.Customer;
import br.edu.catolica.customer_ms.dto.OrderCreatedEventDTO;
import br.edu.catolica.customer_ms.dto.OrderRequestDTO;
import br.edu.catolica.customer_ms.event.OrderEventPubliher;
import br.edu.catolica.customer_ms.exception.CustomerNotFoundException;
import br.edu.catolica.customer_ms.exception.EventOrderException;
import br.edu.catolica.customer_ms.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderEventService {
    private final OrderServicePersistence orderServicePersistence;
    private final CustomerRepository customerRepository;
    private final OrderEventPubliher orderEventPubliher;

    public void send(OrderRequestDTO orderRequestDTO) {
        Customer customer = customerRepository
                .findById(orderRequestDTO.customerId())
                .orElseThrow(() -> {
                    log.warn("m=send, customer not found to id ={}", orderRequestDTO.customerId());
                    return new CustomerNotFoundException("Customer não encontrado");
                });

        OrderRequestDTO request = withOrderCode(orderRequestDTO);
        orderServicePersistence.saveOrder(request);

        OrderCreatedEventDTO eventDTO = OrderCreatedEventDTO.builder()
                .customerEmail(customer.getEmail())
                .customerId(customer.getId())
                .customerName(customer.getName())
                .items(request.items())
                .orderCode(request.orderCode())
                .sellerId(request.sellerId())
                .build();
        try{
            orderEventPubliher.sendOrderRequestEvent(eventDTO);
        } catch (EventOrderException e){
            log.error("m=send, fail try send order to topic, order code = {}", eventDTO.orderCode());
            orderServicePersistence.updateOrderToFailed(eventDTO.orderCode());
            throw e;
        }
    }

    private OrderRequestDTO withOrderCode(OrderRequestDTO orderRequestDTO){
        if(StringUtils.hasText(orderRequestDTO.orderCode())){
            return orderRequestDTO;
        }
        return new OrderRequestDTO(
                orderRequestDTO.sellerId(),
                orderRequestDTO.customerId(),
                orderRequestDTO.items()
        );
    }
}
