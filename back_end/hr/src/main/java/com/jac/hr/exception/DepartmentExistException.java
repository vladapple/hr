package com.jac.hr.exception;

public class DepartmentExistException extends RuntimeException {
    private String message;

    public DepartmentExistException(String message){
        super(message);
    }
}
