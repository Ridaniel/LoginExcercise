package com.inadvance.exercise.application.expose.api.controller.mapper;

import com.inadvance.exercise.application.expose.api.controller.dto.request.CreateUserDto;
import com.inadvance.exercise.domain.model.user.User;
import jakarta.validation.Valid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserDtoMapperTest {

    @Valid
    private CreateUserDto createUserDto;

    @BeforeEach
    public void setup() {
        createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setPassword("password123");
        createUserDto.setPhones(List.of(new CreateUserDto.PhoneDto("123456789", "01", "001")));
    }

    @Test
    public void testFromCreateUserDtoToUser() {
        createUserDto.setEmail("john.doe@example.com");
        User result = UserDtoMapper.fromCreateUserDtoToUser(createUserDto);

        assertEquals("john.doe@example.com", result.getEmail());
    }

}
