package com.tmss.backend.service.impl;

import com.tmss.backend.dto.OrderDto;
import com.tmss.backend.dto.OrderDetailDto;
import com.tmss.backend.entity.*;
import com.tmss.backend.repositories.*;
import com.tmss.backend.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketTemplateRepository ticketTemplateRepository;
    private final OrderRepository orderRepository;
    private final PromotionRepository promotionRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             TicketTemplateRepository ticketTemplateRepository,
                             OrderRepository orderRepository,
                             PromotionRepository promotionRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketTemplateRepository = ticketTemplateRepository;
        this.orderRepository = orderRepository;
        this.promotionRepository = promotionRepository;
    }

    @Override
    @Transactional
    public OrderDto createOrderWithTickets(OrderDto orderDto) {
        // Tạo Order mới
        Order order = new Order();
        order.setCreateDate(LocalDateTime.now());

        // Lấy thông tin Promotion nếu có
        Promotion promotion = orderDto.getPromotionId() != null ?
                promotionRepository.findById(orderDto.getPromotionId()).orElse(null) : null;

        // Danh sách OrderDetail sẽ được tạo ra
        List<OrderDetail> orderDetails = new ArrayList<>();

        // Lặp qua các loại vé và số lượng từ frontend để tạo OrderDetail
        for (OrderDetailDto orderDetailDto : orderDto.getOrderDetails()) {
            // Lấy Ticket theo ID được gửi từ frontend (là ID của Ticket, không phải TicketTemplate)
            Ticket ticket = ticketRepository.findById(orderDetailDto.getTicket())
                    .orElseThrow(() -> new IllegalArgumentException("Ticket not found"));

            // Lấy TicketTemplate từ Ticket
            TicketTemplate ticketTemplate = ticket.getTicketTemplate();

            // Tạo các vé tương ứng với số lượng
            for (int i = 0; i < orderDetailDto.getQuantity(); i++) {
                // Tạo vé mới nếu chưa tồn tại (hoặc lấy vé đã có nếu cần)
                // Không cần tạo lại ticket, chỉ cần sử dụng Ticket đã lấy từ DB

                // Tính giá vé sau khi áp dụng khuyến mãi
                double instantPrice = calculateInstantPrice(ticketTemplate.getPrice(), promotion != null ? promotion.getSalePercent() : 0);

                // Tạo OrderDetail cho vé này
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setTicket(ticket);
                orderDetail.setQuantity(1);  // Mỗi OrderDetail có 1 vé
                orderDetail.setInstantPrice(instantPrice);
                orderDetails.add(orderDetail);
            }
        }

        // Gắn OrderDetail vào Order
        order.setOrderDetails(orderDetails);
        order = orderRepository.save(order);

        // Trả về OrderDto
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .createDate(order.getCreateDate())
                .promotionId(order.getPromotion() != null ? order.getPromotion().getPromotionId() : null)
                .build();
    }

    // Tính giá vé sau khi áp dụng khuyến mãi
    @Override
    public double calculateInstantPrice(double ticketPrice, int discountPercent) {
        if (discountPercent > 0) {
            return ticketPrice * (1 - discountPercent / 100.0);
        }
        return ticketPrice;
    }
}
