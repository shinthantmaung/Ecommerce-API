package com.ecommerce.api.domain.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateProductRequestDTO {
    @NotBlank(message = "name should not be blank")
    @Size(min = 2, max = 50, message = "name should be between {min} and {max} characters")
    @Pattern(regexp= "^[\\s\\w-]+$", message = "only numbers, letters and spaces are allowed")
    private String name;

    @Size(min = 2, max = 300, message = "description should be between {min} and {max} characters")
    @Pattern(regexp= "^[\\s\\w-]+$", message = "only numbers, letters and spaces are allowed")
    private String description;

    @NotNull(message = "product quantity should be specified")
    @Min(value = 1, message = "{quantity should be {min} minimum")
    private Integer quantity;

    @DecimalMin(value = "0", message = "price should not be negative")
    @NotNull(message = "price should be specified")
    private BigDecimal price;

    @NotNull(message = "category should be specified")
    private Long categoryId;

    private String img;
}
