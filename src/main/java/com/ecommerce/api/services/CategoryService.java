package com.ecommerce.api.services;

import com.ecommerce.api.domain.entities.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category createCategory(String name);

    List<Category> createCategories(Set<String> names);

    Category getCategoryById(Long id);

    List<Category> getAllCategories();
}
