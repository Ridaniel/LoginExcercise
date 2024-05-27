package com.inadvance.exercise.domain.error;

public class RepeatedUserEmailException extends RuntimeException {

    public RepeatedUserEmailException(final String errorMessage) {
        super(errorMessage);
    }

}
