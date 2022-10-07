package com.jac.hr.exception;

public class PositionExistException extends RuntimeException {
    private String message;

    public PositionExistException(String message){
        super(message);
    }
}
