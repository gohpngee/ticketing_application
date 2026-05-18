package com.brian.ticketing_app.common.exception.ExceptionClasses;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String message) {
        super(message);
    }
}
