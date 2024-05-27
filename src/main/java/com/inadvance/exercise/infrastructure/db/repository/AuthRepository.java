package com.inadvance.exercise.infrastructure.db.repository;

import com.inadvance.exercise.infrastructure.db.entity.AuthDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<AuthDb, UUID> {

    @Query(value = "SELECT a FROM auth a WHERE a.user.uuid = :userId")
    Optional<AuthDb> findByUserId(@Param("userId") UUID userId);

    @Query(value = "SELECT a FROM auth a JOIN FETCH a.user u WHERE u.email = :email")
    Optional<AuthDb> findByUserEmail(@Param("email") String email);
}