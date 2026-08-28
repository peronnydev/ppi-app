package edu.ppi.seller_ms.service;

import edu.ppi.seller_ms.domain.Seller;
import edu.ppi.seller_ms.dto.SellerDTO;
import edu.ppi.seller_ms.dto.SellerResponseDTO;
import edu.ppi.seller_ms.exception.SellerException;
import edu.ppi.seller_ms.mapper.SellerMapper;
import edu.ppi.seller_ms.repositories.SellerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.ppi.seller_ms.constants.SellerConstants.SELLER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;

    public void save(SellerDTO sellerDTO){
        try{
            Seller seller = sellerMapper.dtoToEntity(sellerDTO);
            sellerRepository.save(seller);
        } catch (Exception e){
            log.error("m=save, error ao tentar salvar o seller com phoneNumber = {}", sellerDTO.phoneNumber());
            throw new SellerException(e.getMessage());
        }
    }

    public List<SellerResponseDTO> findAll(){
        return sellerMapper.entityToResponse(sellerRepository.findAll());
    }

    public SellerResponseDTO findById(Long id){
        return sellerMapper.entityToResponse(findSellerById(id));
    }

    public void update(Long id, SellerDTO sellerDTO){
        Seller seller = findSellerById(id);
        try{
            sellerMapper.updateEntityFromDto(sellerDTO, seller);
            sellerRepository.save(seller);
        } catch (Exception e){
            log.error("m=update, error ao tentar atualizar o seller com id = {}", id);
            throw new SellerException(e.getMessage());
        }
    }

    public void delete(Long id){
        Seller seller = findSellerById(id);
        try{
            sellerRepository.delete(seller);
        } catch (Exception e){
            log.error("m=delete, error ao tentar remover o seller com id = {}", id);
            throw new SellerException(e.getMessage());
        }
    }

    private Seller findSellerById(Long id){
        return sellerRepository.findById(id)
                .orElseThrow(() -> new SellerException(SELLER_NOT_FOUND));
    }
}
