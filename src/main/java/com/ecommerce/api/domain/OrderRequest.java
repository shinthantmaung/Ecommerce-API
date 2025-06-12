package com.ecommerce.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private String address;
    private Payment paymentMethod;
    private String phoneNumber;
    private String regionCode;
}