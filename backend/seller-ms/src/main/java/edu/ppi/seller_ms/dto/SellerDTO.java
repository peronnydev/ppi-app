package edu.ppi.seller_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "SellerDTO", description = "Representação dos dados do seller")
public record SellerDTO(

        @Schema(description = "Nome do vendedor", minLength = 2)
        @NotBlank(message = "Campo name é obrigatório")
        @Size(min = 2, message = "O nome não deve ser menor que 2 caracteres")
        @Size(max = 255, message = "O nome deve ser menor que 255 caracteres")
        String name,

        @Schema(description = "Telefone do vendedor", example = "83981298965")
        @NotBlank(message = "Campo phoneNumber é obrigatório")
        @Size(max = 20, message = "O phoneNumber deve conter no máximo 20 dígitos")
        String phoneNumber
        ){
}
