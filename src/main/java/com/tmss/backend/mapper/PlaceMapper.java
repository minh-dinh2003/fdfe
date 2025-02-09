package com.tmss.backend.mapper;

import com.tmss.backend.dto.PlaceDto;
import com.tmss.backend.entity.Place;

public class PlaceMapper {

    // Chuyển từ Entity sang DTO
    public static PlaceDto mapToPlaceDto(Place place) {
        return PlaceDto.builder()
                .placeId(place.getPlaceId())
                .placeName(place.getPlaceName())
                .address(place.getAddress())
                .placeImg(place.getPlaceImg())
                .build();
    }

    // Chuyển từ DTO sang Entity
    public static Place mapToPlace(PlaceDto placeDto) {
        return Place.builder()
                .placeId(placeDto.getPlaceId())
                .placeName(placeDto.getPlaceName())
                .address(placeDto.getAddress())
                .placeImg(placeDto.getPlaceImg())
                .build();
    }
}
