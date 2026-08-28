package edu.ppi.seller_ms.domain;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ANALYZE("Em análise"),
    ACCEPTED("Aceito"),
    PRODUCTION("Em produção"),
    REFUSED("Recusado"),
    CANCELED("Cancelado"),
    DELIVERY("Saiu para entrega"),
    FAILED_TO_SEND("Falha ao gerar pedido");

    private final String description;

    OrderStatus(String description){
        this.description = description;
    }
}
