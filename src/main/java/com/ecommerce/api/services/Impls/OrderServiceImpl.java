package com.ecommerce.api.services.Impls;

import com.ecommerce.api.domain.DTOs.OrderDTO;
import com.ecommerce.api.domain.OrderRequest;
import com.ecommerce.api.domain.entities.*;
import com.ecommerce.api.repositories.OrderRepository;
import com.ecommerce.api.services.CartService;
import com.ecommerce.api.services.OrderService;
import com.ecommerce.api.services.UserService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    @Override
    @Transactional
    public Order checkout(OrderRequest orderRequest, String email) {
        if(!validatePhoneNumber(orderRequest.getPhoneNumber(), orderRequest.getRegionCode()))
        {
            throw new IllegalArgumentException("Invalid Phone Number");
        }

        User user = userService.getUserByEmail(email);
        Cart cart = cartService.getCartByEmail(email);
        List<CartItem> cartItems = cart.getCartItems();

        Order newOrder = Order.builder()
                .address(orderRequest.getAddress())
                .paymentMethod(orderRequest.getPaymentMethod())
                .phoneNumber(orderRequest.getPhoneNumber())
                .phoneNumberRegionCode(orderRequest.getRegionCode())
                .user(user)
                .build();

        BigDecimal totalPrice = BigDecimal.valueOf(0);
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItem : cartItems)
        {
            if(cartItem.getQuantity() > cartItem.getProduct().getQuantity())
            {
                throw new IllegalStateException("not enough items in stock");
            }

            BigDecimal price = cartItem.getProduct().getPrice();
            BigDecimal finalPrice = price.multiply(BigDecimal.valueOf(cartItem.getQuantity()));
            totalPrice = totalPrice.add(finalPrice);
            OrderItem newOrderItem = OrderItem.builder()
                    .order(newOrder)
                    .price(price)
                    .quantity(cartItem.getQuantity())
                    .product(cartItem.getProduct())
                    .build();
            orderItems.add(newOrderItem);

            //updating product quantity
            Integer updatedProductQuantity = cartItem.getProduct().getQuantity() - cartItem.getQuantity();
            cartItem.getProduct().setQuantity(updatedProductQuantity);
        }

        newOrder.setOrderItems(orderItems);
        newOrder.setTotalPrice(totalPrice);

        cartItems.clear();

        return orderRepository.save(newOrder);
    }

    private boolean validatePhoneNumber(String phoneNumberString, String regionCode)
    {
        try{
            Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse(phoneNumberString, regionCode);
            return phoneNumberUtil.isValidNumber(phoneNumber);
        }
        catch(NumberParseException exp)
        {
            return false;
        }
    }
}
