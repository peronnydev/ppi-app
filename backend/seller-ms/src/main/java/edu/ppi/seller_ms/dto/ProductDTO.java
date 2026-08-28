package edu.ppi.seller_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(name = "ProductDTO", description = "Representação dos dados do product")
public record ProductDTO(

        @Schema(description = "Identificador do produto", example = "1")
        @NotNull(message = "Campo id é obrigatório")
        Long id,

        @Schema(description = "Descrição do produto", example = "Pizza de calabresa")
        @NotBlank(message = "Campo description é obrigatório")
        String description,

        @Schema(description = "Preço do produto", example = "49.90")
        @NotNull(message = "Campo price é obrigatório")
        @Positive(message = "O price deve ser maior que zero")
        BigDecimal price,

        @Schema(description = "Identificador do vendedor", example = "1")
        @NotNull(message = "Campo sellerId é obrigatório")
        Long sellerId
        ){
}
