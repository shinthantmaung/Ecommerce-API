package com.ecommerce.api.repositories;

import com.ecommerce.api.domain.entities.Cart;
import com.ecommerce.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    boolean existsByUser(User user);

    Optional<Cart> findByUser(User user);
}
