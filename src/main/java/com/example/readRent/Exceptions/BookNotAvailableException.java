package com.example.readRent.Exceptions;

public class BookNotAvailableException extends RuntimeException{

    public BookNotAvailableException(){
        super();
    }

    public BookNotAvailableException(String message) {
        super(message);
    }
}
