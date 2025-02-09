package com.tmss.backend.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Integer orderId;
    private LocalDateTime createDate;
    private Integer promotionId;
    private List<OrderDetailDto> orderDetails;
}

