package com.ecommerce.api.domain.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RemoveCartItemRequestDTO {
    @NotNull(message = "productId must not be null")
    private Long productId;
}
