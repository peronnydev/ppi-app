package edu.ppi.seller_ms.mapper;

import edu.ppi.seller_ms.domain.Seller;
import edu.ppi.seller_ms.dto.SellerDTO;
import edu.ppi.seller_ms.dto.SellerResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SellerMapper {
    Seller dtoToEntity(SellerDTO sellerDTO);

    SellerResponseDTO entityToResponse(Seller seller);

    List<SellerResponseDTO> entityToResponse(List<Seller> sellers);

    void updateEntityFromDto(SellerDTO sellerDTO, @MappingTarget Seller seller);
}
