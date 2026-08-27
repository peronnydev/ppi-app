package br.edu.catolica.costumer_ms.mapper;

import br.edu.catolica.costumer_ms.domain.Customer;
import br.edu.catolica.costumer_ms.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {
    Customer dtoToEntity(CustomerDTO customerDTO);
}
