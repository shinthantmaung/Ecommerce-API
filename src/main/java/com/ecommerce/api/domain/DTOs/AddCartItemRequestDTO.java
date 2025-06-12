package com.ecommerce.api.domain.DTOs;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCartItemRequestDTO {
    @NotNull(message = "productId must not be null")
    private Long productId;

    @Min(value = 1,message = "quantity must be at least {min}")
    @NotNull(message = "product quantity must be provided")
    private Integer quantity;

    @NotBlank(message = "email must not be blank")
    @Email(message = "incorrect email format")
    private String email;
}
