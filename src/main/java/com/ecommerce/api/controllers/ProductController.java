package com.ecommerce.api.controllers;

import com.ecommerce.api.domain.CreateProductRequest;
import com.ecommerce.api.domain.DTOs.CategoryDTO;
import com.ecommerce.api.domain.DTOs.CreateProductRequestDTO;
import com.ecommerce.api.domain.DTOs.PageResponseDTO;
import com.ecommerce.api.domain.DTOs.ProductDTO;
import com.ecommerce.api.domain.entities.Category;
import com.ecommerce.api.domain.entities.Product;
import com.ecommerce.api.mappers.ProductMapper;
import com.ecommerce.api.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductMapper productMapper;
    private final ProductService productService;
    @PreAuthorize("hasRole('ROLE_VENDOR')")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(
            @Valid @RequestBody CreateProductRequestDTO createProductRequestDTO,
            @AuthenticationPrincipal OidcUser user
    )
    {
        CreateProductRequest createProductRequest = productMapper.toCreateProductRequest(createProductRequestDTO);
        Product product = productService.createProduct(createProductRequest, user.getEmail());
        ProductDTO productDTO = productMapper.toProductDTO(product);
        System.out.println("product name: " + productDTO.getName());
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageResponseDTO<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    )
    {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Product> pageResult = productService.getAllProducts(pageable);
        return ResponseEntity.ok(
                new PageResponseDTO<>(
                        pageResult.getContent(),
                        pageResult.getNumber(),
                        pageResult.getSize(),
                        pageResult.getTotalElements(),
                        pageResult.getTotalPages(),
                        pageResult.isLast()
                )
        );
    }

}
