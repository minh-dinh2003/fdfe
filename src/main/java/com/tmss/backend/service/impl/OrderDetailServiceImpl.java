package com.tmss.backend.service.impl;

import com.tmss.backend.dto.OrderDetailDto;
import com.tmss.backend.dto.OrderDto;
import com.tmss.backend.entity.Order;
import com.tmss.backend.entity.OrderDetail;
import com.tmss.backend.service.OrderDetailService;
import com.tmss.backend.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private TicketService ticketService;

    @Override
    public void addOrderDetails(List<OrderDetailDto> orderDetailDtos, Order order) {
        // Dùng TicketService để tạo đơn hàng với vé
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderDetails(orderDetailDtos); // Chuyển dữ liệu từ OrderDetailDto sang OrderDto
        orderDto.setPromotionId(order.getPromotion() != null ? order.getPromotion().getPromotionId() : null);

        // Gọi TicketService để tạo Order với vé
        ticketService.createOrderWithTickets(orderDto);
    }

}
