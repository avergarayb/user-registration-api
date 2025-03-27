package com.smartjob.user_registration_api.infrastructure.mappers;

import com.smartjob.user_registration_api.domain.model.Phone;
import com.smartjob.user_registration_api.domain.model.User;
import com.smartjob.user_registration_api.infrastructure.entities.PhoneEntity;
import com.smartjob.user_registration_api.infrastructure.entities.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final UserMapper mapper = new UserMapper();

    @Test
    void toEntity_WithNullInput_ShouldReturnNull() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    void toEntity_WithValidUser_ShouldMapCorrectly() {
        // Arrange
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        Phone phone = Phone.builder()
                .number("123456789")
                .cityCode("1")
                .countryCode("57")
                .build();

        User user = User.builder()
                .id(userId)
                .name("Test User")
                .email("test@example.com")
                .password("password")
                .phones(List.of(phone))
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token("token123")
                .active(true)
                .build();

        // Act
        UserEntity entity = mapper.toEntity(user);

        // Assert
        assertNotNull(entity);
        assertEquals(userId, entity.getId());
        assertEquals("Test User", entity.getName());
        assertEquals("test@example.com", entity.getEmail());
        assertEquals("password", entity.getPassword());
        assertNotNull(entity.getPhones());
        assertEquals(1, entity.getPhones().size());
        assertEquals("123456789", entity.getPhones().get(0).getNumber());
        assertEquals(now, entity.getCreated());
        assertEquals("token123", entity.getToken());
        assertTrue(entity.isActive());
    }

    @Test
    void toDomain_WithNullInput_ShouldReturnNull() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void toDomain_WithValidEntity_ShouldMapCorrectly() {
        // Arrange
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        PhoneEntity phoneEntity = PhoneEntity.builder()
                .number("987654321")
                .cityCode("2")
                .countryCode("58")
                .build();

        UserEntity entity = UserEntity.builder()
                .id(userId)
                .name("Test Entity")
                .email("entity@example.com")
                .password("entityPass")
                .phones(List.of(phoneEntity))
                .created(now)
                .modified(now)
                .lastLogin(now)
                .token("entityToken")
                .active(false)
                .build();

        // Act
        User user = mapper.toDomain(entity);

        // Assert
        assertNotNull(user);
        assertEquals(userId, user.getId());
        assertEquals("Test Entity", user.getName());
        assertEquals("entity@example.com", user.getEmail());
        assertEquals("entityPass", user.getPassword());
        assertNotNull(user.getPhones());
        assertEquals(1, user.getPhones().size());
        assertEquals("987654321", user.getPhones().get(0).getNumber());
        assertEquals(now, user.getCreated());
        assertEquals("entityToken", user.getToken());
        assertFalse(user.isActive());
    }
}
