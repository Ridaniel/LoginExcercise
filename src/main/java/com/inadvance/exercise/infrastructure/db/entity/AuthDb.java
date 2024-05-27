package com.inadvance.exercise.infrastructure.db.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDb {
    @Id
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID id;
    @Column(length = 512)
    private String token;
    private String hashedPassword;
    private String salt;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "uuid")
    private UserDb user;
    private LocalDateTime lastLogin;

}
