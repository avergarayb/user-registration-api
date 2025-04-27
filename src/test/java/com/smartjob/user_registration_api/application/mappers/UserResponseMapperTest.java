package com.smartjob.user_registration_api.application.mappers;

import com.smartjob.user_registration_api.application.dto.UserResponse;
import com.smartjob.user_registration_api.domain.model.Phone;
import com.smartjob.user_registration_api.domain.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserResponseMapperTest {

    private final UserResponseMapper mapper = new UserResponseMapper();

    @Test
    void testToResponseWithNullInput() {
        assertNull(mapper.toResponse(null));
    }

    @Test
    void testToResponseWithValidInput() {
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        Phone phone = Phone.builder()
                .number("987654")
                .cityCode("3")
                .countryCode("59")
                .build();

        User user = User.builder()
                .id(userId)
                .name("Test User")
                .email("test@example.com")
                .phones(List.of(phone))
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token("testToken")
                .active(true)
                .build();

        UserResponse response = mapper.toResponse(user);

        assertNotNull(response);
        assertEquals(userId, response.getId());
        assertEquals(now, response.getCreated());
        assertEquals("testToken", response.getToken());
        assertTrue(response.isActive());
    }
}
