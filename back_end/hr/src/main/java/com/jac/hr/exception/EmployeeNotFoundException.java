package com.jac.hr.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private String message;

    public EmployeeNotFoundException(String message){
        super(message);
    }
}

