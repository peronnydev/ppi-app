package edu.ppi.seller_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SellerResponseDTO", description = "Representação de retorno dos dados do seller")
public record SellerResponseDTO(
        Long id,
        String name,
        String phoneNumber
        ){
}
