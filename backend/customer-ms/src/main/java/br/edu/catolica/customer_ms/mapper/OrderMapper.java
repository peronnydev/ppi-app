package br.edu.catolica.customer_ms.mapper;

import br.edu.catolica.customer_ms.domain.Order;
import br.edu.catolica.customer_ms.dto.OrderRequestDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order dtoToEntity(OrderRequestDTO orderRequestDTO);
}
