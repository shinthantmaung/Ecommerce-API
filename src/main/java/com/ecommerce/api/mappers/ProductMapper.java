package com.ecommerce.api.mappers;

import com.ecommerce.api.domain.CreateProductRequest;
import com.ecommerce.api.domain.DTOs.CreateProductRequestDTO;
import com.ecommerce.api.domain.DTOs.ProductDTO;
import com.ecommerce.api.domain.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.WARN)
public interface ProductMapper {
    ProductDTO toProductDTO(Product product);
    CreateProductRequest toCreateProductRequest(CreateProductRequestDTO createProductRequestDTO);
}
