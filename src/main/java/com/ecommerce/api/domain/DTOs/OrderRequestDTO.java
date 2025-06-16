package com.ecommerce.api.domain.DTOs;

import com.ecommerce.api.domain.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    @NotBlank(message = "address must be provided")
    @Size(min = 2, max = 150, message = "address should be between {min} and {max} characters")
    private String address;

    @NotNull(message = "payment method should be specified")
    private Payment paymentMethod;

    @NotBlank(message = "phone number must be provided")
    @Size(min = 2, max = 20, message = "phone number should be between {min} and {max} characters")
    private String phoneNumber;

    @NotBlank(message = "region code must be provided")
    @Size(min = 1, max = 5, message = "region code should be between {min} and {max} characters")
    private String regionCode;
}
