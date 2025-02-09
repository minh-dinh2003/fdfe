package com.tmss.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ticket")
public class Ticket {
    @Id
    private String ticketId;

    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "templateId")
    private TicketTemplate ticketTemplate;

    private String status;
}
