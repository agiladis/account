package com.example.account.exceptions;

public class UserErrorException extends RuntimeException {

    public UserErrorException(String message) {
        super(message);
    }
}
