package com.tmss.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private String ticketId;
    private LocalDateTime createDate;
    private Integer templateId;
    private String status;
}
