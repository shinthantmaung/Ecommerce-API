package com.ecommerce.api.controllers;

import com.ecommerce.api.domain.DTOs.OrderDTO;
import com.ecommerce.api.domain.DTOs.OrderRequestDTO;
import com.ecommerce.api.domain.OrderRequest;
import com.ecommerce.api.domain.entities.Order;
import com.ecommerce.api.mappers.OrderMapper;
import com.ecommerce.api.services.OrderService;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import jakarta.servlet.http.HttpServlet;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    @PostMapping
    public ResponseEntity<OrderDTO> checkout(
            @Valid @RequestBody OrderRequestDTO orderRequestDTO,
            @AuthenticationPrincipal OidcUser user
    )
    {
        String email = user.getEmail();
        OrderRequest orderRequest = orderMapper.toOrderRequest(orderRequestDTO);
        Order order = orderService.checkout(orderRequest, email);
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);
        return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }
}
