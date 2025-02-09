package com.tmss.backend.service;

import com.tmss.backend.dto.PromotionDto;

import java.util.List;

public interface PromotionService {
    PromotionDto createPromotion(PromotionDto promotionDto);
    PromotionDto getPromotion(Integer id);
    PromotionDto updatePromotion(PromotionDto promotionDto);
    void deletePromotion(Integer id);
    List<PromotionDto> getAllPromotions();
}
