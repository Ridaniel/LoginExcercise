package com.inadvance.exercise.infrastructure.db.mapper;

import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.infrastructure.db.entity.AuthDb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    Auth toDomain(AuthDb entityAuthDb);

    AuthDb toEntity(Auth domainAuth);
}