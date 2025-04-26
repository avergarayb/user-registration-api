package com.smartjob.user_registration_api.application.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserResponseTest {

    @Test
    void testUserResponseCreation() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        UserResponse response = UserResponse.builder()
                .id(userId)
                .name("María García")
                .email("maria@example.com")
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token("token123")
                .active(true)
                .build();

        assertEquals(userId, response.getId());
        assertEquals("María García", response.getName());
        assertEquals("maria@example.com", response.getEmail());
        assertEquals(now, response.getCreated());
        assertEquals("token123", response.getToken());
        assertTrue(response.isActive());
    }
}
