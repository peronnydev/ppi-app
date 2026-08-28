package br.edu.catolica.costumer_ms.service;

import br.edu.catolica.costumer_ms.domain.Customer;
import br.edu.catolica.costumer_ms.dto.CustomerDTO;
import br.edu.catolica.costumer_ms.exception.CustomerException;
import br.edu.catolica.costumer_ms.mapper.CustomerMapper;
import br.edu.catolica.costumer_ms.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public void save(CustomerDTO customerDTO){
        try{
            Customer customer = customerMapper.dtoToEntity(customerDTO);
            customerRepository.save(customer);
        } catch (Exception e){
            log.error("m=save, error ao tentar salvar o customer com CPF = {}", customerDTO.cpf());
            throw new CustomerException(e.getMessage());
        }
    }
}
