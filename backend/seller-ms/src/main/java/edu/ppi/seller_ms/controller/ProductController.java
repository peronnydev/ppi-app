package edu.ppi.seller_ms.controller;

import edu.ppi.seller_ms.dto.ProductDTO;
import edu.ppi.seller_ms.dto.ProductResponseDTO;
import edu.ppi.seller_ms.service.ProductService;
import edu.ppi.seller_ms.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.ppi.seller_ms.constants.ProductConstants.*;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "API Products", description = "Recursos para gerenciamento de products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Cadastrar produto", description = "Cadastra um product na base de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = PRODUCT_MESSAGE_201),
            @ApiResponse(responseCode = "400", description = PRODUCT_MESSAGE_400),
            @ApiResponse(responseCode = "500", description = PRODUCT_MESSAGE_500)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid ProductDTO productDTO){
        productService.save(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED.value(), PRODUCT_MESSAGE_201));
    }

    @Operation(summary = "Listar produtos", description = "Lista todos os products da base de dados")
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> findAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @Operation(summary = "Buscar produto por id", description = "Busca um product pelo seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um product existente")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO){
        productService.update(id, productDTO);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), PRODUCT_MESSAGE_200));
    }

    @Operation(summary = "Remover produto", description = "Remove um product da base de dados")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), PRODUCT_MESSAGE_DELETE));
    }
}
