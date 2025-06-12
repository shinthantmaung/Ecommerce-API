package com.ecommerce.api.mappers;

import com.ecommerce.api.domain.DTOs.OrderDTO;
import com.ecommerce.api.domain.DTOs.OrderRequestDTO;
import com.ecommerce.api.domain.OrderRequest;
import com.ecommerce.api.domain.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)

public interface OrderMapper {
    OrderRequest toOrderRequest(OrderRequestDTO orderRequestDTO);
    OrderDTO toOrderDTO(Order order);
}
