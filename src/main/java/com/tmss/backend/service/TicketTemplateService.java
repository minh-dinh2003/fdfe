package com.tmss.backend.service;

import com.tmss.backend.dto.TicketTemplateDto;
import java.util.List;

public interface TicketTemplateService {
    TicketTemplateDto createTicketTemplate(TicketTemplateDto ticketTemplateDto);
    TicketTemplateDto getTicketTemplate(Integer id);
    TicketTemplateDto updateTicketTemplate(Integer id, TicketTemplateDto updatedTicketTemplateDto);
    List<TicketTemplateDto> getAllTicketTemplates();
}
