package com.tmss.backend.service.impl;

import com.tmss.backend.dto.OrderDto;
import com.tmss.backend.entity.Order;
import com.tmss.backend.entity.Promotion;
import com.tmss.backend.exception.ResourceNotFoundException;
import com.tmss.backend.mapper.OrderMapper;
import com.tmss.backend.repositories.OrderRepository;
import com.tmss.backend.repositories.PromotionRepository;
import com.tmss.backend.service.OrderDetailService;
import com.tmss.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;
    private final PromotionRepository promotionRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Promotion promotion = promotionRepository.findById(orderDto.getPromotionId())
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));

        Order order = Order.builder()
                .createDate(LocalDateTime.now())
                .promotion(promotion)
                .build();

        Order savedOrder = orderRepository.save(order);

        // Save OrderDetails
        orderDetailService.addOrderDetails(orderDto.getOrderDetails(), savedOrder);

        return OrderMapper.mapToOrderDto(savedOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::mapToOrderDto)
                .collect(Collectors.toList());
    }
}

