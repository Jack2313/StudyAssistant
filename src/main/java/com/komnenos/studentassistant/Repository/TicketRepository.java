package com.komnenos.studentassistant.Repository;

import com.komnenos.studentassistant.Entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
