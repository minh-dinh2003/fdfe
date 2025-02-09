package com.tmss.backend.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDto {
    private Integer promotionId;
    private String vehicle;
    private Integer requireNumber;
    private Integer salePercent;
}
