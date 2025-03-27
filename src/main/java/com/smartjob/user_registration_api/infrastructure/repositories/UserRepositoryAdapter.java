package com.smartjob.user_registration_api.infrastructure.repositories;

import com.smartjob.user_registration_api.domain.model.User;
import com.smartjob.user_registration_api.domain.ports.UserRepositoryPort;
import com.smartjob.user_registration_api.infrastructure.entities.UserEntity;
import com.smartjob.user_registration_api.infrastructure.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}
