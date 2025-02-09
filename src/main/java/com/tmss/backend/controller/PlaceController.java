package com.tmss.backend.controller;

import com.tmss.backend.dto.PlaceDto;
import com.tmss.backend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    // API để tạo mới địa điểm
    @PostMapping
    public ResponseEntity<PlaceDto> createPlace(@RequestBody PlaceDto placeDto) {
        PlaceDto createdPlace = placeService.createPlace(placeDto);
        return ResponseEntity.ok(createdPlace);
    }

    // API để lấy thông tin địa điểm theo ID
    @GetMapping("/{placeId}")
    public ResponseEntity<PlaceDto> getPlace(@PathVariable Integer placeId) {
        PlaceDto placeDto = placeService.getPlace(placeId);
        return ResponseEntity.ok(placeDto);
    }

    // API để cập nhật thông tin địa điểm
    @PutMapping("/{placeId}")
    public ResponseEntity<PlaceDto> updatePlace(@PathVariable Integer placeId, @RequestBody PlaceDto updatedPlaceDto) {
        PlaceDto updatedPlace = placeService.updatePlace(placeId, updatedPlaceDto);
        return ResponseEntity.ok(updatedPlace);
    }

    // API để lấy tất cả các địa điểm
    @GetMapping
    public ResponseEntity<List<PlaceDto>> getAllPlaces() {
        List<PlaceDto> places = placeService.getAllPlaces();
        return ResponseEntity.ok(places);
    }
}
