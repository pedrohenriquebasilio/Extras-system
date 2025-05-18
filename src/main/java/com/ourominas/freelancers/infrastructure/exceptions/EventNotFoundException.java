package com.ourominas.freelancers.infrastructure.exceptions;


public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(String message) {
        super(message);
    }
}
