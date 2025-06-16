package com.ecommerce.api.services;

import com.ecommerce.api.domain.entities.Cart;
import com.ecommerce.api.domain.entities.CartItem;
import com.ecommerce.api.domain.entities.Product;
import com.ecommerce.api.domain.entities.User;

public interface CartService {
    Cart createCart(User user);

    Cart getCartByUser(User user);

    Cart getCartByEmail(String email);

    Cart addCartItemToCart(Long productId, Integer quantity, String email);

    Cart removeCartItemFromCart(Long productId, String email);

    Cart updateCartItemQuantity(Integer quantity, String email, Long cartItemId);

    CartItem createCartItem(Cart cart, Product product, Integer quantity);
}
