package com.brian.ticketing_app.common.exception.ExceptionClasses;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }

}
