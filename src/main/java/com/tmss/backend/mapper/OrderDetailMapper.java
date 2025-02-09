package com.tmss.backend.mapper;

import com.tmss.backend.dto.OrderDetailDto;
import com.tmss.backend.entity.Order;
import com.tmss.backend.entity.OrderDetail;
import com.tmss.backend.entity.Ticket;


public class OrderDetailMapper {

    public static OrderDetailDto mapToOrderDetailDto(OrderDetail orderDetail) {
        return OrderDetailDto.builder()
                .id(orderDetail.getId())
                .orderId(orderDetail.getOrder().getOrderId())
                .ticket(orderDetail.getTicket().getTicketId())
                .quantity(orderDetail.getQuantity())
                .instantPrice(orderDetail.getInstantPrice())
                .build();
    }

    public static OrderDetail mapToOrderDetail(OrderDetailDto dto, Order order, Ticket ticket) {
        return OrderDetail.builder()
                .order(order)
                .ticket(ticket)
                .quantity(dto.getQuantity())
                .instantPrice(dto.getInstantPrice())
                .build();
    }
}
