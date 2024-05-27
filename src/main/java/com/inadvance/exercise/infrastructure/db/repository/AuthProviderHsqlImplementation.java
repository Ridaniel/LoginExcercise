package com.inadvance.exercise.infrastructure.db.repository;

import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.domain.outbond.AuthProvider;
import com.inadvance.exercise.infrastructure.db.entity.AuthDb;
import com.inadvance.exercise.infrastructure.db.mapper.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthProviderHsqlImplementation implements AuthProvider {

    private final AuthRepository authRepository;
    private final AuthMapper authMapper;

    @Override
    public Auth getUserAuth(UUID uuid) {
        Optional<AuthDb> authDb = authRepository.findByUserId(uuid);
        if (authDb.isPresent()) {
            return authMapper.toDomain(authDb.get());
        }
        return null;
    }

    @Override
    public Auth saveUserAuth(Auth auth) {
        if (ObjectUtils.isEmpty(auth)) {
            return null;
        }
        AuthDb authDb = authMapper.toEntity(auth);
        authRepository.save(authDb);
        return auth;
    }

    @Override
    public Auth getUserByEmail(String email) {
        Optional<AuthDb> authDb = authRepository.findByUserEmail(email);
        if (authDb.isPresent()) {
            return authMapper.toDomain(authDb.get());
        }
        return null;
    }


}
