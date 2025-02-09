package com.tmss.backend.mapper;

import com.tmss.backend.dto.OrderDto;
import com.tmss.backend.entity.Order;

import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto mapToOrderDto(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .createDate(order.getCreateDate())
                .promotionId(order.getPromotion() != null ? order.getPromotion().getPromotionId() : null)
                .orderDetails(order.getOrderDetails().stream()
                        .map(OrderDetailMapper::mapToOrderDetailDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public static Order mapToOrder(OrderDto dto) {
        return Order.builder()
                .orderId(dto.getOrderId())
                .createDate(dto.getCreateDate())
                .build();
    }
}

