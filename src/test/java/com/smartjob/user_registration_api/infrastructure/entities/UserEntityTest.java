package com.smartjob.user_registration_api.infrastructure.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserEntityTest {

    @Test
    void testUserEntityBuilder() {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        UserEntity user = UserEntity.builder()
                .id(id)
                .name("Test User")
                .email("test@example.com")
                .password("password")
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token("token123")
                .active(true)
                .build();

        assertEquals(id, user.getId());
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(now, user.getCreated());
        assertEquals("token123", user.getToken());
        assertTrue(user.isActive());
    }
}
