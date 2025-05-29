package com.ecommerce.api.domain.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CreateCategoryRequestDTO {
    @NotBlank(message = "category name should not be blank")
    @Size(min = 2, max = 50, message = "category name should be between {min} and {max} characters")
    @Pattern(regexp= "^[\\s\\w-]+$", message = "only numbers, letters and spaces are allowed")
    private String name;
}
