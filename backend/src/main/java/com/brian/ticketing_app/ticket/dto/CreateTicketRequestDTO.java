package com.brian.ticketing_app.ticket.dto;

import com.brian.ticketing_app.ticket.Ticket.TicketPriority;
import com.brian.ticketing_app.ticket.Ticket.TicketStatus;

import lombok.Getter;

@Getter
public class CreateTicketRequestDTO {
    private String ticketTitle;
    private String ticketDescription;
    private TicketPriority ticketPriority;
    private TicketStatus ticketStatus;
}
