package com.example.readRent.Exceptions;

public class UserRentCapacityFullException extends RuntimeException {

    public UserRentCapacityFullException() {
        super();
    }

    public UserRentCapacityFullException(String message) {
        super(message);
    }
}
