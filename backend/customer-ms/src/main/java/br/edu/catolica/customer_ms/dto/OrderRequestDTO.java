package br.edu.catolica.customer_ms.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record OrderRequestDTO (
        @NotNull(message = "O id do vendedor é obrigatório")
        Long sellerId,
        @NotNull(message = "O id do cliente é obrigatório")
        Long customerId,
        String orderCode,
        @NotEmpty(message = "O pedido deve conter ao menos um item")
        List<@Valid ItemRequestDTO> items
){
    public OrderRequestDTO(Long sellerId,
                           Long customerId,
                           List<ItemRequestDTO> items) {
        this(sellerId, customerId, UUID.randomUUID().toString(), items);
    }
}
