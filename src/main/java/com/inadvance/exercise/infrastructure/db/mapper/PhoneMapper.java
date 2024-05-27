package com.inadvance.exercise.infrastructure.db.mapper;

import com.inadvance.exercise.domain.model.user.Phone;
import com.inadvance.exercise.infrastructure.db.entity.PhoneDb;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    Phone toDomain(PhoneDb entityPhoneDb);

    PhoneDb toEntity(Phone domainPhone);
}