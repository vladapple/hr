package com.jac.hr.exception;

public class SeniorityExistException extends RuntimeException {
    private String message;

    public SeniorityExistException(String message){
        super(message);
    }
}
