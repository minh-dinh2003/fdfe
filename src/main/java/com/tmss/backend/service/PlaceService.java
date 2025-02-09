package com.tmss.backend.service;

import com.tmss.backend.dto.PlaceDto;
import java.util.List;

public interface PlaceService {
    PlaceDto createPlace(PlaceDto placeDto);
    PlaceDto getPlace(Integer placeId);
    PlaceDto updatePlace(Integer placeId, PlaceDto updatedPlaceDto);
    List<PlaceDto> getAllPlaces();
}
