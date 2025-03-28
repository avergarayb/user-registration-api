package com.smartjob.user_registration_api.application.services;

import com.smartjob.user_registration_api.application.dto.UserRequest;
import com.smartjob.user_registration_api.application.dto.UserResponse;
import com.smartjob.user_registration_api.application.mappers.UserRequestMapper;
import com.smartjob.user_registration_api.application.mappers.UserResponseMapper;
import com.smartjob.user_registration_api.domain.exceptions.UserAlreadyExistsException;
import com.smartjob.user_registration_api.domain.model.User;
import com.smartjob.user_registration_api.domain.ports.UserServicePort;
import com.smartjob.user_registration_api.domain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServicePort userServicePort;
    private final PasswordEncoder passwordEncoder;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    /**
     * Crea un nuevo usuario en el sistema.
     * @param userRequest contiene la información del usuario a crear
     * @return UserResponse contiene la información del usuario creado
     * @throws UserAlreadyExistsException si el correo electrónico ya está registrado
     */
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        if (userServicePort.existsByEmail(userRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        User user = userRequestMapper.toDomain(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(LocalDateTime.now());
        user.setModified(user.getCreated());
        user.setLastLogin(user.getCreated());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);

        User createdUser = userServicePort.createUser(user);
        return userResponseMapper.toResponse(createdUser);
    }
}
