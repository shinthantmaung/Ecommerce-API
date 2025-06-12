package com.ecommerce.api.domain.DTOs;


import com.ecommerce.api.domain.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CartDTO {
    private Long id;
    private CustomerDTO customer;
    private List<CartItemDTO> cartItems;
}
