package com.inadvance.exercise.application.expose.api.controller.mapper;

import com.inadvance.exercise.application.expose.api.controller.dto.request.CreateUserDto;
import com.inadvance.exercise.domain.model.user.Phone;
import com.inadvance.exercise.domain.model.user.User;

import java.util.stream.Collectors;

public class UserDtoMapper {

    public static User fromCreateUserDtoToUser(CreateUserDto createUserDto) {
        return User.builder()
                .email(createUserDto.getEmail())
                .password(createUserDto.getPassword())
                .name(createUserDto.getName())
                .phones(createUserDto.getPhones().stream()
                        .map(UserDtoMapper::fromPhoneDtoToPhone)
                        .collect(Collectors.toList())
                ).build()
                ;
    }

    public static Phone fromPhoneDtoToPhone(CreateUserDto.PhoneDto phoneDto) {
        return Phone.builder()
                .cityCode(phoneDto.getCityCode())
                .countryCode(phoneDto.getCountryCode())
                .number(phoneDto.getNumber())
                .build();
    }
}
