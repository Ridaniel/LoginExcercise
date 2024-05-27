package com.inadvance.exercise.application.expose.api.controller.mapper;

import com.inadvance.exercise.application.expose.api.controller.dto.response.AuthDto;
import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.domain.model.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthDtoMapperTest {

    private Auth auth;
    private User user;

    @BeforeEach
    public void setup() {
        user = mock(User.class);
        auth = mock(Auth.class);

        // Setup mock behaviors
        when(auth.getId()).thenReturn(UUID.randomUUID());
        when(auth.getLastLogin()).thenReturn(LocalDateTime.of(2024, 5, 26, 12, 0));
        when(user.getCreated()).thenReturn(LocalDateTime.of(2024, 5, 25, 12, 0));
        when(user.getModified()).thenReturn(LocalDateTime.of(2024, 5, 26, 12, 0));
        when(user.getIsActive()).thenReturn(true);

        when(auth.getUser()).thenReturn(user);
    }

    @Test
    public void testToAuthDto() {
        AuthDto result = AuthDtoMapper.toAuthDto(auth);

        assertEquals(auth.getId(), result.getId());
        assertEquals(auth.getUser().getCreated(), result.getCreated());
        assertEquals(auth.getLastLogin(), result.getLast_login());
        assertEquals(auth.getUser().getModified(), result.getModified());
        assertEquals(auth.getUser().getIsActive(), result.getIsActive());
    }
}
