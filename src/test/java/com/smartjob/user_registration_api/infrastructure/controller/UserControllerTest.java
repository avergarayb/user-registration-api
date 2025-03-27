package com.smartjob.user_registration_api.infrastructure.controller;

import com.smartjob.user_registration_api.application.dto.ErrorResponse;
import com.smartjob.user_registration_api.application.dto.UserRequest;
import com.smartjob.user_registration_api.application.dto.UserResponse;
import com.smartjob.user_registration_api.domain.exceptions.UserAlreadyExistsException;
import com.smartjob.user_registration_api.domain.service.UserService;
import com.smartjob.user_registration_api.infrastructure.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void registerUser_WhenValidRequest_ShouldReturnCreated() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setEmail("test@example.com");

        UserResponse response = UserResponse.builder()
                .id(UUID.randomUUID())
                .build();

        when(userService.createUser(request)).thenReturn(response);

        // Act
        ResponseEntity<?> result = userController.registerUser(request);

        // Assert
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(userService).createUser(request);
    }

    @Test
    void registerUser_WhenUserExists_ShouldReturnConflict() {
        // Arrange
        UserRequest request = new UserRequest();
        request.setEmail("existing@example.com");

        when(userService.createUser(request))
                .thenThrow(new UserAlreadyExistsException());

        // Act
        ResponseEntity<?> result = userController.registerUser(request);

        // Assert
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        verify(userService).createUser(request);
    }
}
