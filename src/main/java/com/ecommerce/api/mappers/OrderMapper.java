package com.ecommerce.api.mappers;

import com.ecommerce.api.domain.DTOs.OrderDTO;
import com.ecommerce.api.domain.DTOs.OrderItemDTO;
import com.ecommerce.api.domain.DTOs.OrderDTO;
import com.ecommerce.api.domain.DTOs.OrderRequestDTO;
import com.ecommerce.api.domain.OrderRequest;
import com.ecommerce.api.domain.entities.Order;
import com.ecommerce.api.domain.entities.OrderItem;
import com.ecommerce.api.domain.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)

public interface OrderMapper {
    OrderRequest toOrderRequest(OrderRequestDTO orderRequestDTO);
    @Mapping(target = "customer", source ="user")
    @Mapping(target = "orderItems", source="orderItems", qualifiedByName = "changeOrderItemEntityToDTO")
    OrderDTO toOrderDTO(Order order);

    @Named("changeOrderItemEntityToDTO")
    default List<OrderItemDTO> changeOrderItemEntityToDTO(List<OrderItem> orderItems)
    {
        if(orderItems.isEmpty())
        {
            return new ArrayList<>();
        }

        return orderItems.stream().map(
                orderItem -> OrderItemDTO.builder()
                        .price(orderItem.getPrice())
                        .quantity(orderItem.getQuantity())
                        .id(orderItem.getId())
                        .productId(orderItem.getProduct().getId())
                        .orderId(orderItem.getOrder().getId()).build()
        ).toList();
    }
}
