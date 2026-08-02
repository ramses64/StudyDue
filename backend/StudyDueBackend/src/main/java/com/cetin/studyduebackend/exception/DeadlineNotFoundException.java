package com.cetin.studyduebackend.exception;

public class DeadlineNotFoundException extends RuntimeException {

    public DeadlineNotFoundException(Long id) {
        super("Deadline with id " + id + " id was not found");
    }
}
