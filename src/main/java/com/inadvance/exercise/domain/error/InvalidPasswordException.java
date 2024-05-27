package com.inadvance.exercise.domain.error;

public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(final String errorMessage) {
        super(errorMessage);
    }

}
