package com.inadvance.exercise.domain.error;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(final String errorMessage) {
        super(errorMessage);
    }

}
