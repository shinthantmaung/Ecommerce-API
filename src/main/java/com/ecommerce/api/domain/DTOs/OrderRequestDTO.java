package com.ecommerce.api.domain.DTOs;

import com.ecommerce.api.domain.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private String address;
    private Payment paymentMethod;
    private String phoneNumber;
    private String regionCode;
}
