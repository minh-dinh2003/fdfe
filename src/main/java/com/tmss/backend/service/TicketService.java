package com.tmss.backend.service;

import com.tmss.backend.dto.OrderDto;
import com.tmss.backend.entity.TicketType;

public interface TicketService {
    OrderDto createOrderWithTickets(OrderDto orderDto);
    double calculateInstantPrice(double ticketPrice, int discountPercent);
}
