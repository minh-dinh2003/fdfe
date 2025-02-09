package com.tmss.backend.mapper;

import com.tmss.backend.dto.PromotionDto;
import com.tmss.backend.entity.Promotion;

public class PromotionMapper {

    public static PromotionDto mapToPromotionDto(Promotion promotion) {
        return PromotionDto.builder()
                .promotionId(promotion.getPromotionId())
                .vehicle(promotion.getVehicle())
                .requireNumber(promotion.getRequireNumber())
                .salePercent(promotion.getSalePercent())
                .build();
    }

    public static Promotion mapToPromotion(PromotionDto dto) {
        return Promotion.builder()
                .promotionId(dto.getPromotionId())
                .vehicle(dto.getVehicle())
                .requireNumber(dto.getRequireNumber())
                .salePercent(dto.getSalePercent())
                .build();
    }
}
