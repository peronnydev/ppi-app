package edu.ppi.seller_ms.controller;

import edu.ppi.seller_ms.dto.SellerDTO;
import edu.ppi.seller_ms.dto.SellerResponseDTO;
import edu.ppi.seller_ms.service.SellerService;
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

import static edu.ppi.seller_ms.constants.SellerConstants.*;

@RestController
@RequestMapping("/api/v1/seller")
@Tag(name = "API Sellers", description = "Recursos para gerenciamento de sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @Operation(summary = "Cadastrar vendedor", description = "Cadastra um seller na base de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = SELLER_MESSAGE_201),
            @ApiResponse(responseCode = "400", description = SELLER_MESSAGE_400),
            @ApiResponse(responseCode = "500", description = SELLER_MESSAGE_500)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid SellerDTO sellerDTO){
        sellerService.save(sellerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED.value(), SELLER_MESSAGE_201));
    }

    @Operation(summary = "Listar vendedores", description = "Lista todos os sellers da base de dados")
    @GetMapping
    public ResponseEntity<List<SellerResponseDTO>> findAll(){
        return ResponseEntity.ok(sellerService.findAll());
    }

    @Operation(summary = "Buscar vendedor por id", description = "Busca um seller pelo seu identificador")
    @GetMapping("/{id}")
    public ResponseEntity<SellerResponseDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(sellerService.findById(id));
    }

    @Operation(summary = "Atualizar vendedor", description = "Atualiza os dados de um seller existente")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable Long id, @RequestBody @Valid SellerDTO sellerDTO){
        sellerService.update(id, sellerDTO);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), SELLER_MESSAGE_200));
    }

    @Operation(summary = "Remover vendedor", description = "Remove um seller da base de dados")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable Long id){
        sellerService.delete(id);
        return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), SELLER_MESSAGE_DELETE));
    }
}
