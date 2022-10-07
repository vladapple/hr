package com.jac.hr.exception;

public class SeniorityNotFoundException extends RuntimeException {
    private String message;

    public SeniorityNotFoundException(String message){
        super(message);
    }
}

