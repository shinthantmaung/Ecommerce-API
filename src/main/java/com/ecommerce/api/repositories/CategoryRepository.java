package com.ecommerce.api.repositories;

import com.ecommerce.api.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);

    Set<Category> findByNameInIgnoreCase(Set<String> names);
}
