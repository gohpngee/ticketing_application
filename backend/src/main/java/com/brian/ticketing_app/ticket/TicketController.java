package com.brian.ticketing_app.ticket;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brian.ticketing_app.ticket.dto.CreateTicketRequestDTO;
import com.brian.ticketing_app.ticket.dto.UpdateTicketRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<String> createTicket(@RequestBody CreateTicketRequestDTO createTicketRequestDTO) {
        ticketService.createTicket(createTicketRequestDTO);
        return ResponseEntity.ok("Ticket created successfully");
    }

    @GetMapping("/tickets/{ticketId}")
    public ResponseEntity<Ticket> getTicket(@PathVariable String ticketId) {
        return ResponseEntity.ok(ticketService.getTicket(ticketId));
    }

    @PutMapping("/tickets/{ticketId}")
    public ResponseEntity<String> updateTicket(@PathVariable String ticketId, @RequestBody UpdateTicketRequestDTO updateTicketRequestDTO) {
        ticketService.updateTicket(updateTicketRequestDTO);
        return ResponseEntity.ok("Ticket updated successfully");
    }
    
    @DeleteMapping("/tickets/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable String ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("Ticket deleted successfully");
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }
}
