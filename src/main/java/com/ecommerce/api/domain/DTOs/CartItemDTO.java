package com.ecommerce.api.domain.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartItemDTO {
    private Long id;
    private Long productId;
    private BigDecimal price;
    private Integer quantity;
    private Long cartId;
}
