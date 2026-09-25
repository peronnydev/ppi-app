package br.edu.catolica.customer_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Representação dos itens de um pedido")
public record ItemRequestDTO (
        @Schema(description = "Id do produto do pedido")
        @NotNull(message = "O id do produto é obrigatorio")
        Long productId,
        @Schema(description = "Quantidade de itens do produto")
        @NotNull(message = "A quantidade do Item é obrigatoria")
        @Positive(message = "Quantidade inválida")
        Integer quantity
){
}
