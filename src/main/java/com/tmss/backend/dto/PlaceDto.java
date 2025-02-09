package com.tmss.backend.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    private Integer placeId;
    private String placeName;
    private String address;
    private String placeImg;
}
