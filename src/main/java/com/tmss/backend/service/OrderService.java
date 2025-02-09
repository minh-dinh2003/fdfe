package com.tmss.backend.service;

import com.tmss.backend.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
    List<OrderDto> getAllOrders();
}
