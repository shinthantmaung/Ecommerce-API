package com.ecommerce.api.domain.DTOs;


import com.ecommerce.api.domain.Payment;
import com.ecommerce.api.domain.entities.OrderItem;
import com.ecommerce.api.domain.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderDTO {
    private Long id;

    private CustomerDTO customer;

    private List<OrderItemDTO> orderItems = new ArrayList<>();

    private BigDecimal totalPrice;

    private LocalDateTime orderedAt;

    private String address;

    private Payment paymentMethod;

    private String phoneNumber;

    private String phoneNumberRegionCode;
}
