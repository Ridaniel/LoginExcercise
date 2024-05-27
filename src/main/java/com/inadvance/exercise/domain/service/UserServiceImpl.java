package com.inadvance.exercise.domain.service;

import com.inadvance.exercise.domain.FieldValidator;
import com.inadvance.exercise.domain.error.InvalidPasswordException;
import com.inadvance.exercise.domain.error.RepeatedUserEmailException;
import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.domain.model.user.User;
import com.inadvance.exercise.domain.outbond.AuthProvider;
import com.inadvance.exercise.domain.outbond.UserProvider;
import com.inadvance.exercise.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserServiceInterface {
    public static final String EMAIL_UNAVAILABLE_MESSAGE = "Direccion de email no disponible";
    public static final String INVALID_PASSWORD_MESSAGE = "Contraseña invalida";
    private final UserProvider userProvider;
    private final AuthProvider authProvider;
    private final FieldValidator fieldValidator;

    @Override
    public Auth createUser(User user) {

        if (!fieldValidator.validatePassword(user.getPassword())) {
            throw new InvalidPasswordException(INVALID_PASSWORD_MESSAGE);
        }
        validateUserEmailAvailability(user.getEmail());

        user = prepareNewUser(user);
        String jwtToken = generateJwtToken(user);
        User savedUser = userProvider.saveUser(user);
        Auth newAuthUser = createAuthForUser(savedUser, jwtToken, user.getPassword());
        Auth savedAuth = authProvider.saveUserAuth(newAuthUser);
        return savedAuth;
    }

    private void validateUserEmailAvailability(String email) {
        Auth existingAuthUser = authProvider.getUserByEmail(email);
        if (!isEmailUsed(existingAuthUser)) {
            throw new RepeatedUserEmailException(EMAIL_UNAVAILABLE_MESSAGE);
        }
    }

    private User prepareNewUser(User user) {
        user.setUuid(UUID.randomUUID());
        user.setCreated(LocalDateTime.now());
        user.setIsActive(true);
        user.setModified(LocalDateTime.now());
        return user;
    }

    private Auth createAuthForUser(User user, String jwtToken, String rawPassword) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(rawPassword, salt);
        return Auth.builder()
                .id(UUID.randomUUID())
                .user(user)
                .salt(salt)
                .token(jwtToken)
                .lastLogin(LocalDateTime.now())
                .hashedPassword(hashedPassword)
                .build();
    }

    private String generateJwtToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(User.Fields.uuid, user.getUuid());
        claims.put(User.Fields.email, user.getEmail());
        return JwtUtil.generateToken(claims);
    }

    private boolean isEmailUsed(Auth authUser) {
        return ObjectUtils.isEmpty(authUser);
    }
}
