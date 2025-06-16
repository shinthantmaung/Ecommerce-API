package com.ecommerce.api.domain.DTOs;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UpdateCartItemQuantityRequestDTO {
    @Min(value = 0, message = "quantity value provided should be minimally {min}")
    @NotNull(message = "quantity value should not be null")
    private Integer quantity;
}
