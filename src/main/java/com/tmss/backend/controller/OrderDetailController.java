package com.tmss.backend.controller;

import com.tmss.backend.dto.OrderDetailDto;
import com.tmss.backend.dto.OrderDto;
import com.tmss.backend.entity.Order;
import com.tmss.backend.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    // Endpoint để thêm OrderDetails vào Order
    @PostMapping("/{orderId}/details")
    public ResponseEntity<?> addOrderDetails(
            @PathVariable Integer orderId,
            @RequestBody List<OrderDetailDto> orderDetailDtos) {

        // Tạo đối tượng Order, giả sử bạn lấy Order từ DB theo orderId
        Order order = new Order();
        order.setOrderId(orderId);

        // Thêm OrderDetail vào Order
        try {
            orderDetailService.addOrderDetails(orderDetailDtos, order);
            return ResponseEntity.ok("Order details added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to add order details: " + e.getMessage());
        }
    }

}
