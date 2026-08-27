package br.edu.catolica.costumer_ms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Schema(name = "CustomerDTO", description = "Representação dos dados do customer")
public record CustomerDTO(

        @Schema(description = "Nome do cliente", minLength = 3)
        @NotBlank(message = "Campo name é obrigatório")
        @Size(min = 2, message = "O nome não deve ser menor que 2 caracteres")
        @Size(max = 300, message = "O nome deve ser menor que 300 caracteres")
        String name,

        @Schema(description = "Email do cliente", example = ("cliente@email.com"))
        @Email(message = "email inválido")
        @NotBlank(message = "Campo email é obrigatório")
        String email,

        @Schema(description = "CPF do cliente (somente números)", example = "12345678900")
        @CPF(message = "Cpf inválido")
        @NotBlank(message = "Campo cpf é obrigatório")
        String cpf,

        @Schema(description = "Telefone do cliente", example = "83981298965")
        @NotBlank(message = "Campo phoneNumber é obrigatório")
        @Size(min = 11, max = 11, message = "O phoneNumber deve conter 11 dígitos")
        String phoneNumber,

        AddressDTO address
        ){
}
