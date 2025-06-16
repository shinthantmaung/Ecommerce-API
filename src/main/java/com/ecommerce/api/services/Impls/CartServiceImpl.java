package com.ecommerce.api.services.Impls;

import com.ecommerce.api.domain.entities.Cart;
import com.ecommerce.api.domain.entities.CartItem;
import com.ecommerce.api.domain.entities.Product;
import com.ecommerce.api.domain.entities.User;
import com.ecommerce.api.repositories.CartItemRepository;
import com.ecommerce.api.repositories.CartRepository;
import com.ecommerce.api.services.CartService;
import com.ecommerce.api.services.ProductService;
import com.ecommerce.api.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserService userService;
    private final ProductService productService;

    @Override
    @Transactional
    public CartItem createCartItem(Cart cart, Product product, Integer quantity) {
        if(quantity > product.getQuantity())
        {
            throw new IllegalArgumentException("Oops, product has only " + product.getQuantity() + " items in stock");
        }
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .price(product.getPrice())
                .quantity(quantity)
                .build();
        return cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public Cart addCartItemToCart(Long productId, Integer quantity, String email) {
        User user = userService.getUserByEmail(email);
        Product product = productService.getProductById(productId);
        Cart cart;
        if(!cartRepository.existsByUser(user))
        {
            cart = createCart(user);
        }
        else
        {
            cart = getCartByUser(user);
        }

        List<CartItem> cartItems = cart.getCartItems();

        Optional<CartItem> existingItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            CartItem cartItem = createCartItem(cart, product, quantity);
            cartItems.add(cartItem);
        }

        return cart;
    }

    @Override
    @Transactional
    public Cart removeCartItemFromCart(Long productId, String email) {
        Cart cart = getCartByEmail(email);
        if(cart == null)
        {
            throw new IllegalArgumentException("user does not have cart");
        }
        List<CartItem> cartItems = cart.getCartItems();
        cartItems.removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));
        return cart;
    }

    @Override
    @Transactional
    public Cart updateCartItemQuantity(Integer quantity, String email, Long cartItemId) {
        Cart cart = getCartByEmail(email);
        List<CartItem> cartItems = cart.getCartItems();

        for(CartItem cartItem : cartItems)
        {
            if(cartItem.getId().equals(cartItemId))
            {
                cartItem.setQuantity(quantity);
                break;
            }
        }

        return cart;
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCartByEmail(String email) {
        User user = userService.getUserByEmail(email);
        return getCartByUser(user);
    }

    @Override
    @Transactional
    public Cart createCart(User user) {
        if(cartRepository.existsByUser(user))
        {
            throw new IllegalArgumentException("cart already exists for user");
        }
        Cart newCart = Cart.builder()
                .user(user)
                .build();
        return cartRepository.save(newCart);
    }

    @Override
    @Transactional(readOnly = true)
    public Cart getCartByUser(User user) {
        return cartRepository.findByUser(user).orElseThrow(
                () -> new EntityNotFoundException("cart for user does not exist")
        );
    }
}
