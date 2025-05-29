package com.ecommerce.api.services.Impls;

import com.ecommerce.api.domain.entities.Category;
import com.ecommerce.api.repositories.CategoryRepository;
import com.ecommerce.api.services.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional
    public Category createCategory(String name) {
        if(categoryRepository.existsByNameIgnoreCase(name))
        {
            throw new IllegalArgumentException("category with name already exist");
        }
        Category newCategory = Category.builder().name(name).build();
        return categoryRepository.save(newCategory);
    }

    @Override
    @Transactional
    public List<Category> createCategories(Set<String> names) {
        Set<Category> existingCategories = categoryRepository.findByNameInIgnoreCase(names);
        Set<String> existingCategoryNames = existingCategories.stream().map(Category::getName).map(String::toLowerCase).collect(Collectors.toSet());
        Set<Category> categoriesToCreate = names.stream().filter(name -> !existingCategoryNames.contains(name.toLowerCase())).map(name -> Category.builder().name(name).build()).collect(Collectors.toSet());
        if (categoriesToCreate.isEmpty())
        {
            throw new IllegalArgumentException("categories already exist");
        }
        List<Category> createdCategories = categoryRepository.saveAll(categoriesToCreate);
        createdCategories.addAll(existingCategories);
        return createdCategories;
    }

    @Override
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("category with id is not found"));
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
