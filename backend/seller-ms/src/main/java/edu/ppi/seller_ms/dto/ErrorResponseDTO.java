package edu.ppi.seller_ms.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorResponseDTO(
        String apiPath,
        Integer httpStatusCode,
        String errorMessage,
        LocalDateTime errorTime
    ) {
}
