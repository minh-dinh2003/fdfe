package com.tmss.backend.service.impl;

import com.tmss.backend.dto.PlaceDto;
import com.tmss.backend.entity.Place;
import com.tmss.backend.exception.ResourceNotFoundException;
import com.tmss.backend.mapper.PlaceMapper;
import com.tmss.backend.repositories.PlaceRepository;
import com.tmss.backend.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {
    private final PlaceRepository placeRepository;

    @Override
    public PlaceDto createPlace(PlaceDto placeDto) {
        Place place = PlaceMapper.mapToPlace(placeDto);
        Place savedPlace = placeRepository.save(place);
        return PlaceMapper.mapToPlaceDto(savedPlace);
    }

    @Override
    public PlaceDto getPlace(Integer placeId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found"));
        return PlaceMapper.mapToPlaceDto(place);
    }

    @Override
    public PlaceDto updatePlace(Integer placeId, PlaceDto updatedPlaceDto) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new ResourceNotFoundException("Place not found"));

        place.setPlaceName(updatedPlaceDto.getPlaceName());
        place.setAddress(updatedPlaceDto.getAddress());
        place.setPlaceImg(updatedPlaceDto.getPlaceImg());

        Place savedPlace = placeRepository.save(place);
        return PlaceMapper.mapToPlaceDto(savedPlace);
    }

    @Override
    public List<PlaceDto> getAllPlaces() {
        return placeRepository.findAll().stream()
                .map(PlaceMapper::mapToPlaceDto)
                .collect(Collectors.toList());
    }
}
