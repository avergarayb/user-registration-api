package com.smartjob.user_registration_api.domain.service;

import com.smartjob.user_registration_api.application.dto.UserRequest;
import com.smartjob.user_registration_api.application.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}
