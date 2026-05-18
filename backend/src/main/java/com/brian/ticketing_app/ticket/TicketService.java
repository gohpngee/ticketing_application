package com.brian.ticketing_app.ticket;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brian.ticketing_app.common.exception.ExceptionClasses.TicketNotFoundException;
import com.brian.ticketing_app.ticket.dto.CreateTicketRequestDTO;
import com.brian.ticketing_app.ticket.dto.UpdateTicketRequestDTO;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@Builder
@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Transactional
    public void createTicket(CreateTicketRequestDTO createTicketRequestDTO) {
        String newId = "ticket-" + (ticketRepository.count() + 1);

        Ticket ticket = Ticket.builder()
            .ticketId(newId)
            .ticketTitle(createTicketRequestDTO.getTicketTitle())
            .ticketDescription(createTicketRequestDTO.getTicketDescription())
            .ticketPriority(createTicketRequestDTO.getTicketPriority())
            .ticketStatus(createTicketRequestDTO.getTicketStatus())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        ticketRepository.save(ticket);
    }

    @Transactional
    public void updateTicket(UpdateTicketRequestDTO updateTicketRequestDTO) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findByTicketId(updateTicketRequestDTO.getTicketId())
            .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticket.setTicketTitle(updateTicketRequestDTO.getTicketTitle());
        ticket.setTicketDescription(updateTicketRequestDTO.getTicketDescription());
        ticket.setTicketPriority(updateTicketRequestDTO.getTicketPriority());
        ticket.setTicketStatus(updateTicketRequestDTO.getTicketStatus());
        ticket.setUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    @Transactional
    public void deleteTicket(String ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findByTicketId(ticketId)
            .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        ticketRepository.delete(ticket);
    }

    public Ticket getTicket(String ticketId) throws TicketNotFoundException {
        return ticketRepository.findByTicketId(ticketId)
            .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
