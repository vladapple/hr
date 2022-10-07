package com.jac.hr.exception;

public class PositionNotFoundException extends RuntimeException {
    private String message;

    public PositionNotFoundException(String message){
        super(message);
    }
}

