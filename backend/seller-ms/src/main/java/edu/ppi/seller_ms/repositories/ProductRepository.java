package edu.ppi.seller_ms.repositories;

import edu.ppi.seller_ms.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
