package com.ourominas.freelancers.infrastructure.exceptions;


public class ExtraNaoDisponivelException extends RuntimeException {
    public ExtraNaoDisponivelException(String message) {
        super(message);
    }
}
