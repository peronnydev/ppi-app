package edu.ppi.seller_ms.mapper;

import edu.ppi.seller_ms.domain.Product;
import edu.ppi.seller_ms.dto.ProductDTO;
import edu.ppi.seller_ms.dto.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller.id", source = "sellerId")
    Product dtoToEntity(ProductDTO productDTO);

    @Mapping(target = "sellerId", source = "seller.id")
    ProductResponseDTO entityToResponse(Product product);

    List<ProductResponseDTO> entityToResponse(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "stock", ignore = true)
    void updateEntityFromDto(ProductDTO productDTO, @MappingTarget Product product);
}
