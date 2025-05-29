package com.ecommerce.api.domain.DTOs;

import com.ecommerce.api.domain.entities.Category;
import com.ecommerce.api.domain.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ProductDTO {
    private Long id;

    private String name;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private String img;

    private Category category;

    private User vendor;
}
