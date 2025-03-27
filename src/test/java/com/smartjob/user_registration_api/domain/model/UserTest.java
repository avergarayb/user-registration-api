package com.smartjob.user_registration_api.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private static final UUID TEST_UUID = UUID.randomUUID();
    private static final String TEST_NAME = "John Doe";
    private static final String TEST_EMAIL = "john.doe@example.com";
    private static final String TEST_PASSWORD = "SecurePassword123";
    private static final String TEST_TOKEN = "test-token-123";
    private static final LocalDateTime TEST_DATE = LocalDateTime.now();

    @Test
    void testNoArgsConstructor() {
        User user = new User();

        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getPhones());
        assertNull(user.getCreated());
        assertNull(user.getModified());
        assertNull(user.getLastLogin());
        assertNull(user.getToken());
        assertFalse(user.isActive());
    }

    @Test
    void testAllArgsConstructor() {
        Phone phone = Phone.builder()
                .number("123456789")
                .cityCode("1")
                .countryCode("57")
                .build();

        User user = new User(
                TEST_UUID,
                TEST_NAME,
                TEST_EMAIL,
                TEST_PASSWORD,
                List.of(phone),
                TEST_DATE,
                TEST_DATE,
                TEST_DATE,
                TEST_TOKEN,
                true
        );

        assertEquals(TEST_UUID, user.getId());
        assertEquals(TEST_NAME, user.getName());
        assertEquals(TEST_EMAIL, user.getEmail());
        assertEquals(TEST_PASSWORD, user.getPassword());
        assertNotNull(user.getPhones());
        assertEquals(1, user.getPhones().size());
        assertEquals(TEST_DATE, user.getCreated());
        assertEquals(TEST_DATE, user.getModified());
        assertEquals(TEST_DATE, user.getLastLogin());
        assertEquals(TEST_TOKEN, user.getToken());
        assertTrue(user.isActive());
    }

    @Test
    void testBuilder() {
        Phone phone = Phone.builder()
                .number("987654321")
                .cityCode("2")
                .countryCode("58")
                .build();

        User user = User.builder()
                .id(TEST_UUID)
                .name(TEST_NAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .phones(List.of(phone))
                .created(TEST_DATE)
                .modified(TEST_DATE)
                .lastLogin(TEST_DATE)
                .token(TEST_TOKEN)
                .active(true)
                .build();

        assertEquals(TEST_UUID, user.getId());
        assertEquals(TEST_NAME, user.getName());
        assertEquals(TEST_EMAIL, user.getEmail());
        assertEquals(TEST_PASSWORD, user.getPassword());
        assertNotNull(user.getPhones());
        assertEquals(1, user.getPhones().size());
        assertEquals("987654321", user.getPhones().get(0).getNumber());
        assertEquals(TEST_DATE, user.getCreated());
        assertEquals(TEST_DATE, user.getModified());
        assertEquals(TEST_DATE, user.getLastLogin());
        assertEquals(TEST_TOKEN, user.getToken());
        assertTrue(user.isActive());
    }

    @Test
    void testSettersAndGetters() {
        Phone phone = new Phone("555555555", "3", "59");
        LocalDateTime now = LocalDateTime.now();

        User user = new User();
        user.setId(TEST_UUID);
        user.setName(TEST_NAME);
        user.setEmail(TEST_EMAIL);
        user.setPassword(TEST_PASSWORD);
        user.setPhones(List.of(phone));
        user.setCreated(now);
        user.setModified(now);
        user.setLastLogin(now);
        user.setToken(TEST_TOKEN);
        user.setActive(true);

        assertEquals(TEST_UUID, user.getId());
        assertEquals(TEST_NAME, user.getName());
        assertEquals(TEST_EMAIL, user.getEmail());
        assertEquals(TEST_PASSWORD, user.getPassword());
        assertNotNull(user.getPhones());
        assertEquals(1, user.getPhones().size());
        assertEquals("555555555", user.getPhones().get(0).getNumber());
        assertEquals(now, user.getCreated());
        assertEquals(now, user.getModified());
        assertEquals(now, user.getLastLogin());
        assertEquals(TEST_TOKEN, user.getToken());
        assertTrue(user.isActive());
    }

    @Test
    void testEqualsAndHashCode() {
        Phone phone1 = new Phone("123456789", "1", "57");
        Phone phone2 = new Phone("123456789", "1", "57");

        User user1 = User.builder()
                .id(TEST_UUID)
                .name(TEST_NAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .phones(List.of(phone1))
                .created(TEST_DATE)
                .modified(TEST_DATE)
                .lastLogin(TEST_DATE)
                .token(TEST_TOKEN)
                .active(true)
                .build();

        User user2 = User.builder()
                .id(TEST_UUID)
                .name(TEST_NAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .phones(List.of(phone2))
                .created(TEST_DATE)
                .modified(TEST_DATE)
                .lastLogin(TEST_DATE)
                .token(TEST_TOKEN)
                .active(true)
                .build();

        User user3 = User.builder()
                .id(UUID.randomUUID())
                .name("Different Name")
                .email("different@email.com")
                .password("DifferentPassword")
                .phones(List.of(new Phone("987654321", "2", "58")))
                .created(LocalDateTime.now().plusDays(1))
                .modified(LocalDateTime.now().plusDays(1))
                .lastLogin(LocalDateTime.now().plusDays(1))
                .token("different-token")
                .active(false)
                .build();

        // Test equals
        assertEquals(user1, user2);
        assertNotEquals(user1, user3);

        // Test hashCode
        assertEquals(user1.hashCode(), user2.hashCode());
        assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    void testToString() {
        User user = User.builder()
                .id(TEST_UUID)
                .name(TEST_NAME)
                .email(TEST_EMAIL)
                .build();

        String userString = user.toString();

        assertTrue(userString.contains("id=" + TEST_UUID));
        assertTrue(userString.contains("name=" + TEST_NAME));
        assertTrue(userString.contains("email=" + TEST_EMAIL));
    }

    @Test
    void testPhoneListModification() {
        User user = User.builder()
                .phones(List.of(new Phone("123456789", "1", "57")))
                .build();

        // Verificar que la lista de phones es inmutable
        assertThrows(UnsupportedOperationException.class, () -> {
            user.getPhones().add(new Phone("987654321", "2", "58"));
        });
    }
}
