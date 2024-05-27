package com.inadvance.exercise.infrastructure.db.repository;

import com.inadvance.exercise.infrastructure.db.entity.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDb, UUID> {
    Optional<UserDb> findByUuid(UUID uuid);

    Optional<UserDb> findByEmail(String email);
}