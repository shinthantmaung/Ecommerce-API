package com.ecommerce.api.services;


import com.ecommerce.api.domain.DTOs.OrderDTO;
import com.ecommerce.api.domain.DTOs.OrderRequestDTO;
import com.ecommerce.api.domain.OrderRequest;
import com.ecommerce.api.domain.entities.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



public interface OrderService {
    Order checkout(OrderRequest orderRequest, String email);
}
