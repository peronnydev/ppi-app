package edu.ppi.seller_ms.repositories;

import edu.ppi.seller_ms.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
