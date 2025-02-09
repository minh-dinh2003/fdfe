package com.tmss.backend.dto;

import com.tmss.backend.entity.TicketType;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketTemplateDto {
    private Integer id;
    private Boolean status;
    private TicketType ticketType;
    private Integer placeId;
    private double price;
}
