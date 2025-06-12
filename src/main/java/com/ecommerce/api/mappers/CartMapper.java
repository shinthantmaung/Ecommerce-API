package com.ecommerce.api.mappers;

import com.ecommerce.api.domain.DTOs.CartDTO;
import com.ecommerce.api.domain.DTOs.CartItemDTO;
import com.ecommerce.api.domain.entities.Cart;
import com.ecommerce.api.domain.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CartMapper {
    @Mapping(target = "customer", source ="user")
    @Mapping(target = "cartItems", source="cartItems", qualifiedByName = "changeCartItemEntityToDTO")
    CartDTO toCartDTO(Cart cart);

    @Named("changeCartItemEntityToDTO")
    default List<CartItemDTO> changeCartItemEntityToDTO(List<CartItem> cartItems)
    {
        if(cartItems.isEmpty())
        {
            return new ArrayList<>();
        }

        return cartItems.stream().map(
                cartItem -> CartItemDTO.builder()
                        .price(cartItem.getPrice())
                        .quantity(cartItem.getQuantity())
                        .id(cartItem.getId())
                        .productId(cartItem.getProduct().getId())
                        .cartId(cartItem.getCart().getId()).build()
        ).toList();
    }
}
