package com.inadvance.exercise.application.expose.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inadvance.exercise.SecurityConfig;
import com.inadvance.exercise.application.expose.api.controller.dto.request.CreateUserDto;
import com.inadvance.exercise.application.expose.api.controller.mapper.UserDtoMapper;
import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.domain.service.UserServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthenticationController.class)
@Import(SecurityConfig.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceInterface userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void authenticate_ValidInput_ReturnsOk() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setPassword("password@123");
        createUserDto.setEmail("john.doe@example.com");
        createUserDto.setPhones(new ArrayList<>());

        Auth auth = Auth.builder()
                .id(UUID.randomUUID())
                .user(UserDtoMapper.fromCreateUserDtoToUser(createUserDto))
                .build();
        when(userService.createUser(any())).thenReturn(auth);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    public void authenticate_InvalidEmail_ReturnsBadRequest() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setName("John Doe");
        createUserDto.setPassword("password123");
        createUserDto.setEmail("john");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createUserDto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
