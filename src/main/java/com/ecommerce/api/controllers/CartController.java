package com.ecommerce.api.controllers;

import com.ecommerce.api.domain.DTOs.AddCartItemRequestDTO;
import com.ecommerce.api.domain.DTOs.CartDTO;
import com.ecommerce.api.domain.DTOs.RemoveCartItemRequestDTO;
import com.ecommerce.api.domain.DTOs.UpdateCartItemQuantityRequestDTO;
import com.ecommerce.api.domain.entities.Cart;
import com.ecommerce.api.mappers.CartMapper;
import com.ecommerce.api.services.CartService;
import jakarta.el.ELManager;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDTO> addCartItemToCart(
            @Valid @RequestBody AddCartItemRequestDTO addCartItemRequestDTO
    )
    {
        Cart cart = cartService.addCartItemToCart(addCartItemRequestDTO.getProductId(),addCartItemRequestDTO.getQuantity(),addCartItemRequestDTO.getEmail());
        CartDTO cartDTO = cartMapper.toCartDTO(cart);
        return ResponseEntity.ok(cartDTO);
    }

    @GetMapping
    public ResponseEntity<CartDTO> getCart(
            @AuthenticationPrincipal OidcUser user
    )
    {
        String email = user.getEmail();
        Cart cart = cartService.getCartByEmail(email);
        CartDTO cartDTO = cartMapper.toCartDTO(cart);
        return ResponseEntity.ok(cartDTO);
    }

    @DeleteMapping
    public ResponseEntity<CartDTO> removeCartItemFromCart(
            @Valid @RequestBody RemoveCartItemRequestDTO removeCartItemRequestDTO,
            @AuthenticationPrincipal OidcUser user
    )
    {
        String email = user.getEmail();
        Cart cart = cartService.removeCartItemFromCart(removeCartItemRequestDTO.getProductId(), email);
        CartDTO cartDTO = cartMapper.toCartDTO(cart);
        return ResponseEntity.ok(cartDTO);
    }

    @PatchMapping(path = "/items/{id}")
    public ResponseEntity<CartDTO> updateCartItemQuantity(
            @PathVariable Long id,
            @RequestBody @Valid UpdateCartItemQuantityRequestDTO updateCartItemQuantityRequestDTO,
            @AuthenticationPrincipal OidcUser user
    )
    {
        String email = user.getEmail();
        Cart cart = cartService.updateCartItemQuantity(updateCartItemQuantityRequestDTO.getQuantity(), email, id);
        CartDTO cartDTO = cartMapper.toCartDTO(cart);
        return ResponseEntity.ok(cartDTO);
    }

}
