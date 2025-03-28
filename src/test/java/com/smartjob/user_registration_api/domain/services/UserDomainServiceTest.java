package com.smartjob.user_registration_api.domain.services;

import com.smartjob.user_registration_api.domain.exceptions.UserAlreadyExistsException;
import com.smartjob.user_registration_api.domain.model.User;
import com.smartjob.user_registration_api.domain.ports.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserDomainServiceTest {

    @Mock
    private UserRepositoryPort userRepository;

    @InjectMocks
    private UserDomainService userService;

    @Test
    void createUser_WhenUserDoesNotExist_ShouldSaveUser() {
        // Arrange
        User user = User.builder()
                .email("test@example.com")
                .build();

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.createUser(user);

        // Assert
        assertNotNull(result);
        verify(userRepository).existsByEmail("test@example.com");
        verify(userRepository).save(user);
    }

    @Test
    void createUser_WhenUserExists_ShouldThrowException() {
        // Arrange
        User user = User.builder()
                .email("existing@example.com")
                .build();

        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> {
            userService.createUser(user);
        });

        verify(userRepository).existsByEmail("existing@example.com");
        verify(userRepository, never()).save(any());
    }

    @Test
    void existsByEmail_ShouldDelegateToRepository() {
        // Arrange
        String email = "test@example.com";
        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        boolean result = userService.existsByEmail(email);

        // Assert
        assertTrue(result);
        verify(userRepository).existsByEmail(email);
    }
}
