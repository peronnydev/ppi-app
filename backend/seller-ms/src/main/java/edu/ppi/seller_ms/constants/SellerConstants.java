package edu.ppi.seller_ms.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SellerConstants {
    public static final String SELLER_MESSAGE_201 = "Vendedor cadastrado com sucesso";
    public static final String SELLER_MESSAGE_200 = "Vendedor atualizado com sucesso";
    public static final String SELLER_MESSAGE_DELETE = "Vendedor removido com sucesso";
    public static final String SELLER_NOT_FOUND = "Vendedor não encontrado";
    public static final String SELLER_MESSAGE_400 = "Entrada de dados inválida";
    public static final String SELLER_MESSAGE_500 = "Erro interno";
}
