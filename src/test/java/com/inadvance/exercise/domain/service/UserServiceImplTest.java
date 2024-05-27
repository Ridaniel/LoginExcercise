package com.inadvance.exercise.domain.service;

import com.inadvance.exercise.domain.FieldValidator;
import com.inadvance.exercise.domain.error.InvalidPasswordException;
import com.inadvance.exercise.domain.error.RepeatedUserEmailException;
import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.domain.model.user.User;
import com.inadvance.exercise.domain.outbond.AuthProvider;
import com.inadvance.exercise.domain.outbond.UserProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private static final String JWT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJ1dWlkIjoiYTYzYjY5ODAtZjU2My00ZmU5LWI3MTQtNDYwM2MxNTc3ZDgwIiwiZW1haWwiOiJhc2RnbWFpbDNjQGZmLmNvbSIsInN1YiI6ImE2M2I2OTgwLWY1NjMtNGZlOS1iNzE0LTQ2MDNjMTU3N2Q4MCIsImlhdCI6MTcxNjc3MTM0OCwiZXhwIjoxNzE2ODU3NzQ4fQ.hDGpmebGufz7pHsHAPeklfx2ZKo9IIr34iXuqTWRE_Y";
    @Mock
    private UserProvider userProvider;
    @Mock
    private AuthProvider authProvider;
    @Mock
    private FieldValidator fieldValidator;
    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private Auth auth;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("Valid123!");

        auth = new Auth();
        auth.setUser(user);
        when(fieldValidator.validatePassword(anyString())).thenReturn(true);
    }

    @Test
    void createUser_ValidUser_ReturnsAuth() {
        // Execute
        when(authProvider.saveUserAuth(any())).thenReturn(auth);
        Auth result = userService.createUser(user);

        // Validate
        assertNotNull(result);
        verify(userProvider).saveUser(any(User.class));
        verify(authProvider).saveUserAuth(any(Auth.class));
    }

    @Test
    void createUser_InvalidPassword_ThrowsException() {
        // Setup the validator to return false for password validity
        when(fieldValidator.validatePassword(anyString())).thenReturn(false);

        // Execute & Validate
        assertThrows(InvalidPasswordException.class, () -> userService.createUser(user));
    }

    @Test
    void createUser_EmailAlreadyUsed_ThrowsException() {
        // Setup to simulate that the email is already used
        when(authProvider.getUserByEmail(anyString())).thenReturn(new Auth());

        // Execute & Validate
        assertThrows(RepeatedUserEmailException.class, () -> userService.createUser(user));
    }
}
