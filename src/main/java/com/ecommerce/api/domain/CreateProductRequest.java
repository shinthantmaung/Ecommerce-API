package com.ecommerce.api.domain;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateProductRequest {
    private String name;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private Long categoryId;

    private String img;
}
