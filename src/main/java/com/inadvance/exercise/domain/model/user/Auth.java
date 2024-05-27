package com.inadvance.exercise.domain.model.user;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    private UUID id;
    private String token;
    private String hashedPassword;
    private String salt;
    private User user;
    private LocalDateTime lastLogin;
}
