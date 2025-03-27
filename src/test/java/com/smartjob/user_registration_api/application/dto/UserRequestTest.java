package com.smartjob.user_registration_api.application.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRequestTest {

    @Test
    void testUserRequestCreation() {
        UserRequest.PhoneRequest phone = UserRequest.PhoneRequest.builder()
                .number("1234567")
                .cityCode("1")
                .countryCode("57")
                .build();

        UserRequest request = UserRequest.builder()
                .name("Juan Pérez")
                .email("juan@example.com")
                .password("Password123")
                .phones(List.of(phone))
                .build();

        assertEquals("Juan Pérez", request.getName());
        assertEquals("juan@example.com", request.getEmail());
        assertEquals("Password123", request.getPassword());
        assertNotNull(request.getPhones());
        assertEquals(1, request.getPhones().size());
        assertEquals("1234567", request.getPhones().get(0).getNumber());
    }

    @Test
    void testPhoneRequestSetters() {
        UserRequest.PhoneRequest phone = new UserRequest.PhoneRequest();
        phone.setNumber("987654");
        phone.setCityCode("2");
        phone.setCountryCode("58");

        assertEquals("987654", phone.getNumber());
        assertEquals("2", phone.getCityCode());
        assertEquals("58", phone.getCountryCode());
    }
}
