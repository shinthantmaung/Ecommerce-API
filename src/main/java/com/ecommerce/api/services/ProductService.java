package com.ecommerce.api.services;


import com.ecommerce.api.domain.CreateProductRequest;
import com.ecommerce.api.domain.entities.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(CreateProductRequest createProductRequest, String vendorEmail);

    List<Product> getAllProducts();
}
