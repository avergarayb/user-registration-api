package com.smartjob.user_registration_api.application.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserResponseTest {

    @Test
    void testUserResponseCreation() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        UserResponse.PhoneResponse phone = UserResponse.PhoneResponse.builder()
                .number("7654321")
                .cityCode("3")
                .countryCode("59")
                .build();

        UserResponse response = UserResponse.builder()
                .id(userId)
                .name("María García")
                .email("maria@example.com")
                .phones(List.of(phone))
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token("token123")
                .active(true)
                .build();

        assertEquals(userId, response.getId());
        assertEquals("María García", response.getName());
        assertEquals("maria@example.com", response.getEmail());
        assertNotNull(response.getPhones());
        assertEquals(1, response.getPhones().size());
        assertEquals("7654321", response.getPhones().get(0).getNumber());
        assertEquals(now, response.getCreated());
        assertEquals("token123", response.getToken());
        assertTrue(response.isActive());
    }
}
