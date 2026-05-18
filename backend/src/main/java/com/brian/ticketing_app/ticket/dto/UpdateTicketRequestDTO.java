package com.brian.ticketing_app.ticket.dto;

import com.brian.ticketing_app.ticket.Ticket.TicketPriority;
import com.brian.ticketing_app.ticket.Ticket.TicketStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateTicketRequestDTO {
    private String ticketId;
    private String ticketTitle;
    private String ticketDescription;
    private TicketPriority ticketPriority;
    private TicketStatus ticketStatus;
}
