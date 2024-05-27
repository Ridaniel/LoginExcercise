package com.inadvance.exercise.infrastructure.db.mapper;

import com.inadvance.exercise.domain.model.user.User;
import com.inadvance.exercise.infrastructure.db.entity.UserDb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserDb entityUserDb);

    UserDb toEntity(User domainUser);
}