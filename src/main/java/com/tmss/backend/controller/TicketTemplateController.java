package com.tmss.backend.controller;

import com.tmss.backend.dto.TicketTemplateDto;
import com.tmss.backend.service.TicketTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket-templates")
@RequiredArgsConstructor
public class TicketTemplateController {

    private final TicketTemplateService ticketTemplateService;

    // API để tạo mới template ticket
    @PostMapping
    public ResponseEntity<TicketTemplateDto> createTicketTemplate(@RequestBody TicketTemplateDto ticketTemplateDto) {
        TicketTemplateDto createdTicketTemplate = ticketTemplateService.createTicketTemplate(ticketTemplateDto);
        return ResponseEntity.ok(createdTicketTemplate);
    }

    // API để lấy thông tin template ticket theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TicketTemplateDto> getTicketTemplate(@PathVariable Integer id) {
        TicketTemplateDto ticketTemplateDto = ticketTemplateService.getTicketTemplate(id);
        return ResponseEntity.ok(ticketTemplateDto);
    }

    // API để cập nhật thông tin template ticket
    @PutMapping("/{id}")
    public ResponseEntity<TicketTemplateDto> updateTicketTemplate(@PathVariable Integer id, @RequestBody TicketTemplateDto updatedTicketTemplateDto) {
        TicketTemplateDto updatedTicketTemplate = ticketTemplateService.updateTicketTemplate(id, updatedTicketTemplateDto);
        return ResponseEntity.ok(updatedTicketTemplate);
    }

    // API để lấy tất cả các template ticket
    @GetMapping
    public ResponseEntity<List<TicketTemplateDto>> getAllTicketTemplates() {
        List<TicketTemplateDto> ticketTemplates = ticketTemplateService.getAllTicketTemplates();
        return ResponseEntity.ok(ticketTemplates);
    }
}
