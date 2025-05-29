package com.ecommerce.api.mappers;

import com.ecommerce.api.domain.DTOs.CategoryDTO;
import com.ecommerce.api.domain.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface CategoryMapper {
    CategoryDTO toCategoryDTO(Category category);
}
