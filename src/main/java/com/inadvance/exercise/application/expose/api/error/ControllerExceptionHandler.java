package com.inadvance.exercise.application.expose.api.error;

import com.inadvance.exercise.domain.error.InvalidPasswordException;
import com.inadvance.exercise.domain.error.RepeatedUserEmailException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ControllerExceptionHandler {

    public static final String ERROR_DESCONOCIDO = "Error desconocido";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(Exception e) {
        return ResponseEntity
                .badRequest()
                .body(Error
                        .builder()
                        .mensaje(e.getMessage())
                        .build())
                ;
    }

    @ExceptionHandler(RepeatedUserEmailException.class)
    public ResponseEntity<Error> handleException(RepeatedUserEmailException e) {
        return ResponseEntity
                .badRequest()
                .body(Error
                        .builder()
                        .mensaje(e.getMessage())
                        .build())
                ;
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<Error> handleException(InvalidPasswordException e) {
        return ResponseEntity
                .badRequest()
                .body(Error
                        .builder()
                        .mensaje(e.getMessage())
                        .build());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .badRequest()
                .body(Error
                        .builder()
                        .mensaje(Objects.requireNonNull(e.getFieldError()).getDefaultMessage())
                        .build())
                ;
    }
}
