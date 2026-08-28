package br.edu.catolica.costumer_ms.controller;

import br.edu.catolica.costumer_ms.dto.CustomerDTO;
import br.edu.catolica.costumer_ms.service.CustomerService;
import br.edu.catolica.costumer_ms.dto.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.edu.catolica.costumer_ms.constants.CustomerConstants.*;

@RestController
@RequestMapping("/api/v1/customer")
@Tag(name = "API Customers", description = "Recursos para gerenciamento de customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @Operation(summary = "Cadastrar cliente", description = "Cadastra um customer na base de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = CUSTOMER_MESSAGE_201),
            @ApiResponse(responseCode = "400", description = CUSTOMER_MESSAGE_400),
            @ApiResponse(responseCode = "500", description = CUSTOMER_MESSAGE_500)
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> creat(@RequestBody @Valid CustomerDTO customerDTO){
        customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDTO(CUSTOMER_MESSAGE_201, HttpStatus.CREATED.value()));
    }
}
