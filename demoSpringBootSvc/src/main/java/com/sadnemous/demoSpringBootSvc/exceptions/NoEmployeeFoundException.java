package com.sadnemous.demoSpringBootSvc.exceptions;

public class NoEmployeeFoundException extends RuntimeException {
    public NoEmployeeFoundException(String message, Throwable err) {
        super(message, err);
    }
}
