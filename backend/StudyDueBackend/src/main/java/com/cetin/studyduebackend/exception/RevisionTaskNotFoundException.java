package com.cetin.studyduebackend.exception;

public class RevisionTaskNotFoundException extends RuntimeException {
    public RevisionTaskNotFoundException(Long id) {
        super("Revision task with id " + id + " was not found.");
    }
}
