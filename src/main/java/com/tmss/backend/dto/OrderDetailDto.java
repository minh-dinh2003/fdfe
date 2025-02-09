package com.tmss.backend.dto;

import lombok.*;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {
    private Integer id;
    private Integer orderId;
    private String ticket;
    private Integer quantity;
    private double instantPrice;
}
