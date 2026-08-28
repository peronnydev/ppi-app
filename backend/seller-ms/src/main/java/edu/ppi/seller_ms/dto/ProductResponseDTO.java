package edu.ppi.seller_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "ProductResponseDTO", description = "Representação de retorno dos dados do product")
public record ProductResponseDTO(
        Long id,
        String description,
        BigDecimal price,
        Long sellerId,
        boolean stock
        ){
}
