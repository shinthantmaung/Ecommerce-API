package com.ecommerce.api.controllers;

import com.ecommerce.api.domain.DTOs.CategoryDTO;
import com.ecommerce.api.domain.DTOs.CreateCategoryRequestDTO;
import com.ecommerce.api.domain.entities.Category;
import com.ecommerce.api.mappers.CategoryMapper;
import com.ecommerce.api.repositories.CategoryRepository;
import com.ecommerce.api.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/single")
    public ResponseEntity<CategoryDTO> createCategory(
            @Valid @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO
    )
    {
        Category createdCategory = categoryService.createCategory(createCategoryRequestDTO.getName());
        CategoryDTO createdCategoryDTO = categoryMapper.toCategoryDTO(createdCategory);
        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/multiple")
    public ResponseEntity<List<CategoryDTO>> createCategories(
            @Valid @RequestBody Set<CreateCategoryRequestDTO> createCategoryRequestDTOs
    )
    {
        Set<String> newCategoryNames = createCategoryRequestDTOs.stream().map(CreateCategoryRequestDTO::getName).collect(Collectors.toSet());
        List<Category> createdCategories = categoryService.createCategories(newCategoryNames);
        List<CategoryDTO> createdCategoryDTOs = createdCategories.stream().map(categoryMapper::toCategoryDTO).toList();
        return new ResponseEntity<>(createdCategoryDTOs, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories()
    {
        List<Category> allCategories = categoryService.getAllCategories();
        List<CategoryDTO> allCategoryDTOs = allCategories.stream().map(categoryMapper::toCategoryDTO).toList();
        return ResponseEntity.ok(allCategoryDTOs);
    }
}
