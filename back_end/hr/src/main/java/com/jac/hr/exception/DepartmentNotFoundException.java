package com.jac.hr.exception;

public class DepartmentNotFoundException extends RuntimeException {
    private String message;

    public DepartmentNotFoundException(String message){
        super(message);
    }
}

