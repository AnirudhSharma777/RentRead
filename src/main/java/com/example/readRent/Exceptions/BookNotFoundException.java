package com.example.readRent.Exceptions;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}
