package com.tmss.backend.service.impl;

import com.tmss.backend.dto.TicketTemplateDto;
import com.tmss.backend.entity.TicketTemplate;
import com.tmss.backend.exception.ResourceNotFoundException;
import com.tmss.backend.mapper.TicketTemplateMapper;
import com.tmss.backend.repositories.TicketTemplateRepository;
import com.tmss.backend.service.TicketTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketTemplateServiceImpl implements TicketTemplateService {
    private final TicketTemplateRepository ticketTemplateRepository;

    @Override
    public TicketTemplateDto createTicketTemplate(TicketTemplateDto ticketTemplateDto) {
        TicketTemplate ticketTemplate = TicketTemplateMapper.mapToTicketTemplate(ticketTemplateDto);
        TicketTemplate savedTicketTemplate = ticketTemplateRepository.save(ticketTemplate);
        return TicketTemplateMapper.mapToTicketTemplateDto(savedTicketTemplate);
    }

    @Override
    public TicketTemplateDto getTicketTemplate(Integer id) {
        TicketTemplate ticketTemplate = ticketTemplateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TicketTemplate not found"));
        return TicketTemplateMapper.mapToTicketTemplateDto(ticketTemplate);
    }

    @Override
    public TicketTemplateDto updateTicketTemplate(Integer id, TicketTemplateDto updatedTicketTemplateDto) {
        TicketTemplate ticketTemplate = ticketTemplateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("TicketTemplate not found"));

        ticketTemplate.setStatus(updatedTicketTemplateDto.getStatus());
        ticketTemplate.setTicketType(updatedTicketTemplateDto.getTicketType());
        ticketTemplate.setPlaceId(updatedTicketTemplateDto.getPlaceId());
        ticketTemplate.setPrice(updatedTicketTemplateDto.getPrice());

        TicketTemplate savedTicketTemplate = ticketTemplateRepository.save(ticketTemplate);
        return TicketTemplateMapper.mapToTicketTemplateDto(savedTicketTemplate);
    }

    @Override
    public List<TicketTemplateDto> getAllTicketTemplates() {
        return ticketTemplateRepository.findAll().stream()
                .map(TicketTemplateMapper::mapToTicketTemplateDto)
                .collect(Collectors.toList());
    }
}
