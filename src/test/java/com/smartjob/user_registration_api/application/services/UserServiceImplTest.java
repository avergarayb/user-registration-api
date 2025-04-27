package com.smartjob.user_registration_api.application.services;

import com.smartjob.user_registration_api.application.dto.UserRequest;
import com.smartjob.user_registration_api.application.dto.UserResponse;
import com.smartjob.user_registration_api.application.mappers.UserRequestMapper;
import com.smartjob.user_registration_api.application.mappers.UserResponseMapper;
import com.smartjob.user_registration_api.domain.exceptions.UserAlreadyExistsException;
import com.smartjob.user_registration_api.domain.model.User;
import com.smartjob.user_registration_api.domain.ports.UserServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserServicePort userServicePort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRequestMapper userRequestMapper;

    @Mock
    private UserResponseMapper userResponseMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void createUser_WhenUserDoesNotExist_ShouldCreateUser() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("password");

        User domainUser = new User();
        domainUser.setName("Test User");
        domainUser.setEmail("test@example.com");
        domainUser.setPassword("password");

        User savedUser = new User();
        savedUser.setId(UUID.randomUUID());
        savedUser.setName("Test User");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encodedPassword");
        savedUser.setToken("generatedToken");
        savedUser.setActive(true);

        UserResponse expectedResponse = new UserResponse();
        expectedResponse.setId(savedUser.getId());
        expectedResponse.setToken("generatedToken");
        expectedResponse.setActive(true);

        when(userServicePort.existsByEmail("test@example.com")).thenReturn(false);
        when(userRequestMapper.toDomain(request)).thenReturn(domainUser);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");
        when(userServicePort.createUser(any(User.class))).thenReturn(savedUser);
        when(userResponseMapper.toResponse(savedUser)).thenReturn(expectedResponse);

        // Act
        UserResponse result = userService.createUser(request);

        // Assert
        assertNotNull(result);
        assertEquals(savedUser.getId(), result.getId());
        assertEquals("generatedToken", result.getToken());
        assertTrue(result.isActive());

        verify(userServicePort).existsByEmail("test@example.com");
        verify(userRequestMapper).toDomain(request);
        verify(passwordEncoder).encode("password");
        verify(userServicePort).createUser(any(User.class));
        verify(userResponseMapper).toResponse(savedUser);
    }

    @Test
    void createUser_WhenUserExists_ShouldThrowException() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setEmail("existing@example.com");

        when(userServicePort.existsByEmail("existing@example.com")).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createUser(request);
        });

        verify(userServicePort).existsByEmail("existing@example.com");
        verifyNoMoreInteractions(userServicePort, passwordEncoder, userRequestMapper, userResponseMapper);
    }

    @Test
    void createUser_ShouldSetCorrectTimestamps() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("password");

        User domainUser = new User();
        domainUser.setName("Test User");
        domainUser.setEmail("test@example.com");
        domainUser.setPassword("password");

        when(userServicePort.existsByEmail("test@example.com")).thenReturn(false);
        when(userRequestMapper.toDomain(request)).thenReturn(domainUser);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        // Capturar el usuario que se está guardando para verificar las fechas
        User[] capturedUser = new User[1];
        when(userServicePort.createUser(any(User.class))).thenAnswer(invocation -> {
            capturedUser[0] = invocation.getArgument(0);
            return capturedUser[0];
        });

        // Act
        userService.createUser(request);

        // Assert
        assertNotNull(capturedUser[0]);
        assertNotNull(capturedUser[0].getCreated());
        assertNotNull(capturedUser[0].getModified());
        assertNotNull(capturedUser[0].getLastLogin());
        assertEquals(capturedUser[0].getCreated(), capturedUser[0].getModified());
        assertEquals(capturedUser[0].getCreated(), capturedUser[0].getLastLogin());
    }
}
