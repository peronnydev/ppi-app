package edu.ppi.seller_ms.service;

import edu.ppi.seller_ms.domain.Product;
import edu.ppi.seller_ms.domain.Seller;
import edu.ppi.seller_ms.dto.ProductDTO;
import edu.ppi.seller_ms.dto.ProductResponseDTO;
import edu.ppi.seller_ms.exception.ProductException;
import edu.ppi.seller_ms.mapper.ProductMapper;
import edu.ppi.seller_ms.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static edu.ppi.seller_ms.constants.ProductConstants.PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public void save(ProductDTO productDTO){
        try{
            Product product = productMapper.dtoToEntity(productDTO);
            productRepository.save(product);
        } catch (Exception e){
            log.error("m=save, error ao tentar salvar o product do seller com sellerId = {}", productDTO.sellerId());
            throw new ProductException(e.getMessage());
        }
    }

    public List<ProductResponseDTO> findAll(){
        return productMapper.entityToResponse(productRepository.findAll());
    }

    public ProductResponseDTO findById(Long id){
        return productMapper.entityToResponse(findProductById(id));
    }

    public void update(Long id, ProductDTO productDTO){
        Product product = findProductById(id);
        try{
            productMapper.updateEntityFromDto(productDTO, product);
            product.setSeller(Seller.builder().id(productDTO.sellerId()).build());
            productRepository.save(product);
        } catch (Exception e){
            log.error("m=update, error ao tentar atualizar o product com id = {}", id);
            throw new ProductException(e.getMessage());
        }
    }

    public void delete(Long id){
        Product product = findProductById(id);
        try{
            productRepository.delete(product);
        } catch (Exception e){
            log.error("m=delete, error ao tentar remover o product com id = {}", id);
            throw new ProductException(e.getMessage());
        }
    }

    private Product findProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductException(PRODUCT_NOT_FOUND));
    }
}
