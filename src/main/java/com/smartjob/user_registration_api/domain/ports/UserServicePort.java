package com.smartjob.user_registration_api.domain.ports;

import com.smartjob.user_registration_api.domain.model.User;

public interface UserServicePort {
    User createUser(User user);
    boolean existsByEmail(String email);
}
