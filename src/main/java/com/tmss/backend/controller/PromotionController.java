package com.tmss.backend.controller;

import com.tmss.backend.dto.PromotionDto;
import com.tmss.backend.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/promotions")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping
    public ResponseEntity<PromotionDto> createPromotion(@RequestBody PromotionDto promotionDto) {
        PromotionDto createdPromotion = promotionService.createPromotion(promotionDto);
        return new ResponseEntity<>(createdPromotion, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromotionDto> getPromotion(@PathVariable Integer id) {
        PromotionDto promotion = promotionService.getPromotion(id);
        return ResponseEntity.ok(promotion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromotionDto> updatePromotion(@PathVariable Integer id, @RequestBody PromotionDto promotionDto) {
        promotionDto.setPromotionId(id);
        PromotionDto updatedPromotion = promotionService.updatePromotion(promotionDto);
        return ResponseEntity.ok(updatedPromotion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Integer id) {
        promotionService.deletePromotion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PromotionDto>> getAllPromotions() {
        List<PromotionDto> promotions = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotions);
    }
}
