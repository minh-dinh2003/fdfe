package com.tmss.backend.repositories;

import com.tmss.backend.entity.TicketTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTemplateRepository extends JpaRepository<TicketTemplate, Integer> {
    TicketTemplate findByTicketType(int typeId);
}
