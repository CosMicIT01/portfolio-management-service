package de.cosmicit.pms.controller.exception;

public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public ResourceNotFoundException(String errorMessage, Throwable ex) {
        super(errorMessage, ex);
    }
}
