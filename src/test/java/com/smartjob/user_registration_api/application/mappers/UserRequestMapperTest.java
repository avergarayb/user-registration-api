package com.smartjob.user_registration_api.application.mappers;

import com.smartjob.user_registration_api.application.dto.UserRequest;
import com.smartjob.user_registration_api.domain.model.Phone;
import com.smartjob.user_registration_api.domain.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserRequestMapperTest {

    private final UserRequestMapper mapper = new UserRequestMapper();

    @Test
    void testToDomainWithNullInput() {
        assertNull(mapper.toDomain(null));
    }

    @Test
    void testToDomainWithValidInput() {
        UserRequest.PhoneRequest phoneRequest = new UserRequest.PhoneRequest();
        phoneRequest.setNumber("123456");
        phoneRequest.setCityCode("1");
        phoneRequest.setCountryCode("57");

        UserRequest request = new UserRequest();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setPhones(List.of(phoneRequest));

        User user = mapper.toDomain(request);

        assertNotNull(user);
        assertEquals("Test User", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getPhones());
        assertEquals(1, user.getPhones().size());
        assertEquals("123456", user.getPhones().get(0).getNumber());
    }

    @Test
    void testToDomainWithoutPhones() {
        UserRequest request = new UserRequest();
        request.setName("Test User");
        request.setEmail("test@example.com");
        request.setPassword("password");

        User user = mapper.toDomain(request);

        assertNotNull(user);
        assertNull(user.getPhones());
    }
}
