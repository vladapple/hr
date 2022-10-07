package com.jac.hr.exception;

public class EmployeeExistException extends RuntimeException {
    private String message;

    public EmployeeExistException(String message){
        super(message);
    }
}
