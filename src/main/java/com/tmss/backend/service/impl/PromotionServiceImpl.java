package com.tmss.backend.service.impl;

import com.tmss.backend.dto.PromotionDto;
import com.tmss.backend.entity.Promotion;
import com.tmss.backend.exception.ResourceNotFoundException;
import com.tmss.backend.mapper.PromotionMapper;
import com.tmss.backend.repositories.PromotionRepository;
import com.tmss.backend.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {
    private final PromotionRepository promotionRepository;

    @Override
    public PromotionDto createPromotion(PromotionDto promotionDto) {
        Promotion promotion = PromotionMapper.mapToPromotion(promotionDto);
        Promotion savedPromotion = promotionRepository.save(promotion);
        return PromotionMapper.mapToPromotionDto(savedPromotion);
    }

    @Override
    public PromotionDto getPromotion(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        return PromotionMapper.mapToPromotionDto(promotion);
    }

    @Override
    public PromotionDto updatePromotion(PromotionDto promotionDto) {
        Promotion promotion = promotionRepository.findById(promotionDto.getPromotionId())
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));

        promotion.setVehicle(promotionDto.getVehicle());
        promotion.setRequireNumber(promotionDto.getRequireNumber());
        promotion.setSalePercent(promotionDto.getSalePercent());

        Promotion savedPromotion = promotionRepository.save(promotion);
        return PromotionMapper.mapToPromotionDto(savedPromotion);
    }

    @Override
    public void deletePromotion(Integer id) {
        Promotion promotion = promotionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Promotion not found"));
        promotionRepository.delete(promotion);
    }

    @Override
    public List<PromotionDto> getAllPromotions() {
        return promotionRepository.findAll().stream()
                .map(PromotionMapper::mapToPromotionDto)
                .collect(Collectors.toList());
    }
}
