package com.inadvance.exercise.domain.outbond;

import com.inadvance.exercise.domain.model.user.Auth;

import java.util.UUID;

public interface AuthProvider {
    Auth getUserAuth(UUID uuid);

    Auth saveUserAuth(Auth auth);

    Auth getUserByEmail(String email);
}
