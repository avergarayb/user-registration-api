package com.smartjob.user_registration_api.domain.ports;

import com.smartjob.user_registration_api.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);
    boolean existsByEmail(String email);
}
