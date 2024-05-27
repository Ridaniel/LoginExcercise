package com.inadvance.exercise.domain.service;

import com.inadvance.exercise.domain.model.user.Auth;
import com.inadvance.exercise.domain.model.user.User;

public interface UserServiceInterface {
    Auth createUser(User user);
}
