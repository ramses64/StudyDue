package com.cetin.studyduebackend.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String jwtSubject) {
        super("The user with the subject " + jwtSubject + " was not found.");
    }
}
