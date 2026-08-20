package br.edu.catolica.costumer_ms.dto;

public record CustomerDTO(
        String name,
        String email,
        String cpf,
        String phoneNumber,
        AddressDTO address
        ){
}
