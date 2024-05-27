package com.inadvance.exercise.infrastructure.db.repository;

import com.inadvance.exercise.domain.model.user.User;
import com.inadvance.exercise.domain.outbond.UserProvider;
import com.inadvance.exercise.infrastructure.db.entity.UserDb;
import com.inadvance.exercise.infrastructure.db.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserProviderHsqlImplementation implements UserProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User getUserByEmail(String email) {
        Optional<UserDb> userDb = userRepository.findByEmail(email);
        return userDb.map(userMapper::toDomain).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        if (ObjectUtils.isEmpty(user)) {
            return null;
        }
        UserDb userDb = userMapper.toEntity(user);
        userRepository.save(userDb);
        return user;
    }
}
