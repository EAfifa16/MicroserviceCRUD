package com.tama.edu.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {
        super("Resource not find on customer server !!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
