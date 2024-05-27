package com.inadvance.exercise.application.expose.api.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {

    private String mensaje;
}
