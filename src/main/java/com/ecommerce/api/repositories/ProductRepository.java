package com.ecommerce.api.repositories;

import com.ecommerce.api.domain.entities.Product;
import com.ecommerce.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByVendorAndNameIgnoreCase(User vendor, String name);
}
