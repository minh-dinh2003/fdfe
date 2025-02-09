package com.tmss.backend.service;

import com.tmss.backend.dto.OrderDetailDto;
import com.tmss.backend.dto.OrderDto;
import com.tmss.backend.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDetailService {
    void addOrderDetails(List<OrderDetailDto> orderDetailDtos, Order order);
}
