package com.inadvance.exercise.application.expose.api.controller.mapper;

import com.inadvance.exercise.application.expose.api.controller.dto.response.AuthDto;
import com.inadvance.exercise.domain.model.user.Auth;

public class AuthDtoMapper {

    public static AuthDto toAuthDto(Auth auth) {
        return AuthDto.builder()
                .id(auth.getId())
                .created(auth.getUser().getCreated())
                .last_login(auth.getLastLogin())
                .modified(auth.getUser().getModified())
                .token(auth.getToken())
                .isActive(auth.getUser().getIsActive())
                .build();
    }
}
