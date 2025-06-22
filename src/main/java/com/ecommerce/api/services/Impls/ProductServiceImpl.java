package com.ecommerce.api.services.Impls;

import com.ecommerce.api.domain.CreateProductRequest;
import com.ecommerce.api.domain.entities.Category;
import com.ecommerce.api.domain.entities.Product;
import com.ecommerce.api.domain.entities.User;
import com.ecommerce.api.repositories.ProductRepository;
import com.ecommerce.api.services.CategoryService;
import com.ecommerce.api.services.ProductService;
import com.ecommerce.api.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    @Override
    @Transactional
    public Product createProduct(CreateProductRequest createProductRequest, String vendorEmail) {
        User vendor = userService.getUserByEmail(vendorEmail);
        Category category = categoryService.getCategoryById(createProductRequest.getCategoryId());
        if (productRepository.existsByVendorAndNameIgnoreCase(vendor, createProductRequest.getName()))
        {
            throw new IllegalArgumentException("product with the same name from the same vendor already exists");
        }
        Product newProduct = Product.builder()
                .name(createProductRequest.getName())
                .description(createProductRequest.getDescription())
                .price(createProductRequest.getPrice())
                .quantity(createProductRequest.getQuantity())
                .category(category)
                .vendor(vendor)
                .img(createProductRequest.getImg())
                .build();
        return productRepository.save(newProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getAllProducts(Pageable productPageable) {
        return productRepository.findAll(productPageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("product with id not found")
        );
    }

}
