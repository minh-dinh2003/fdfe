package com.tmss.backend.mapper;

import com.tmss.backend.dto.TicketTemplateDto;
import com.tmss.backend.entity.TicketTemplate;

public class TicketTemplateMapper {

    // Chuyển từ Entity sang DTO
    public static TicketTemplateDto mapToTicketTemplateDto(TicketTemplate ticketTemplate) {
        return TicketTemplateDto.builder()
                .id(ticketTemplate.getId())
                .status(ticketTemplate.getStatus())
                .ticketType(ticketTemplate.getTicketType())
                .placeId(ticketTemplate.getPlaceId())
                .price(ticketTemplate.getPrice())
                .build();
    }

    // Chuyển từ DTO sang Entity
    public static TicketTemplate mapToTicketTemplate(TicketTemplateDto ticketTemplateDto) {
        return TicketTemplate.builder()
                .id(ticketTemplateDto.getId())
                .status(ticketTemplateDto.getStatus())
                .ticketType(ticketTemplateDto.getTicketType())
                .placeId(ticketTemplateDto.getPlaceId())
                .price(ticketTemplateDto.getPrice())
                .build();
    }
}
