package com.inadvance.exercise.infrastructure.db.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDb {
    @Id
    private UUID uuid;
    private String email;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<PhoneDb> phones;
}
