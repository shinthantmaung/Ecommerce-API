package com.ecommerce.api.services;


import com.ecommerce.api.domain.CreateProductRequest;
import com.ecommerce.api.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest createProductRequest, String vendorEmail);

    List<Product> getAllProducts();

     Page<Product> getAllProducts(Pageable productPageable);

    Product getProductById(Long id);
}
