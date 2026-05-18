package com.brian.ticketing_app.ticket;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Ticket {

    /** Prefix for the public ticket id; the numeric part comes from {@link #id} (DB auto-increment). */
    public static final String TICKET_ID_PREFIX = "ticket-";
    
    public enum TicketPriority {
        LOW,
        MEDIUM,
        HIGH
    }

    public enum TicketStatus {
        OPEN,
        IN_PROGRESS,
        RESOLVED,
        CLOSED
    }

    /**
     * Surrogate primary key; the database assigns the next integer on insert.
     * The human-facing id is {@link #getTicketId()} ({@value #TICKET_ID_PREFIX} + this value).
     */
    @JsonIgnore
    @Id
    private String ticketId;

    @Column(nullable = false)
    private String ticketTitle;

    @Column(nullable = false)
    private String ticketDescription;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketPriority ticketPriority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus ticketStatus;
}
