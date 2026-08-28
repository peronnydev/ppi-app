package edu.ppi.seller_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "SellerProductsDTO", description = "Representação de um seller e seus produtos")
public record SellerProductsDTO(
        String name,
        List<ProductDTO> products
        ){
}
