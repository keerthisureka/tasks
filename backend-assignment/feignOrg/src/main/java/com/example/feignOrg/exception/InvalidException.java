package com.example.feignOrg.exception;

public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}
