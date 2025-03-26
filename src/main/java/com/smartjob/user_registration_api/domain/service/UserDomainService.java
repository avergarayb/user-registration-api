package com.smartjob.user_registration_api.domain.service;

import com.smartjob.user_registration_api.domain.exceptions.UserAlreadyExistsException;
import com.smartjob.user_registration_api.domain.model.User;
import com.smartjob.user_registration_api.domain.ports.UserRepositoryPort;
import com.smartjob.user_registration_api.domain.ports.UserServicePort;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService implements UserServicePort {

    private final UserRepositoryPort userRepository;

    public UserDomainService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
