package com.inadvance.exercise.application.expose.api.controller.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class CreateUserDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password is required")
    private String password;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    private List<PhoneDto> phones;

    @RequiredArgsConstructor
    @Getter
    @Setter
    public static class PhoneDto {
        private String number;
        private String cityCode;
        private String countryCode;

        public PhoneDto(String number, String number1, String number2) {
        }
    }
}
