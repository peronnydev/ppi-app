package br.edu.catolica.costumer_ms.repositories;

import br.edu.catolica.costumer_ms.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
