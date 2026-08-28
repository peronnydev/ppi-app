package br.edu.catolica.costumer_ms.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(
        String apiPatch,
        Integer httpStatus,
        String message,
        LocalDateTime errorTime
    ) {
}
