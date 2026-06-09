package com.brian.ticketing_app.ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findByTicketId(String ticketId);
    
    // Ticket has a ManyToOne relationship named `ticketOwner` (User).
    // Query by the foreign key `ticketOwner.id`.
    List<Ticket> findByTicketOwner_Id(Long userId);
}
