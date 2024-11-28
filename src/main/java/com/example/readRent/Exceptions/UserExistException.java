package com.example.readRent.Exceptions;

public class UserExistException extends RuntimeException {

    public UserExistException() {
        super();
    }

    public UserExistException(String message) {
        super(message);
    }
}
