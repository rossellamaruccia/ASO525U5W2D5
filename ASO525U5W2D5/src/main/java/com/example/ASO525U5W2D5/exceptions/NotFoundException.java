package com.example.ASO525U5W2D5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super(
                "Could not find element with id" + id
        );
    }

    public NotFoundException(String message) {
        super(message);
    }
}
