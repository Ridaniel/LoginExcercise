package com.inadvance.exercise.domain.outbond;

import com.inadvance.exercise.domain.model.user.User;

public interface UserProvider {
    User getUserByEmail(String email);

    User saveUser(User user);
}
