package com.inadvance.exercise.application.expose.api.controller;

import com.inadvance.exercise.application.expose.api.controller.dto.request.CreateUserDto;
import com.inadvance.exercise.application.expose.api.controller.dto.response.AuthDto;
import com.inadvance.exercise.application.expose.api.controller.mapper.AuthDtoMapper;
import com.inadvance.exercise.application.expose.api.controller.mapper.UserDtoMapper;
import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.domain.service.UserServiceInterface;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
@Tag(name = "User Management System", description = "CreateUser")
public class AuthenticationController {

    private final UserServiceInterface userService;


    @PostMapping
    public ResponseEntity<AuthDto> authenticate(@Valid @RequestBody CreateUserDto createUserDto) {
        Auth authUser = userService.createUser(UserDtoMapper.fromCreateUserDtoToUser(createUserDto));

        return ResponseEntity.ok(AuthDtoMapper.toAuthDto(authUser));
    }

}
