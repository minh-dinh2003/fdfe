package com.tmss.backend.mapper;

import com.tmss.backend.dto.TicketDto;
import com.tmss.backend.dto.TicketTemplateDto;
import com.tmss.backend.entity.Ticket;
import com.tmss.backend.entity.TicketTemplate;

public class TicketMapper {

    public static TicketDto mapToTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .ticketId(ticket.getTicketId())
                .createDate(ticket.getCreateDate())
                .templateId(ticket.getTicketTemplate().getId())
                .status(ticket.getStatus())
                .build();
    }

    public static Ticket mapToTicket(TicketDto ticketDto, TicketTemplate ticketTemplate) {
        return Ticket.builder()
                .ticketId(ticketDto.getTicketId())
                .createDate(ticketDto.getCreateDate())
                .ticketTemplate(ticketTemplate)
                .status(ticketDto.getStatus())
                .build();
    }
}
