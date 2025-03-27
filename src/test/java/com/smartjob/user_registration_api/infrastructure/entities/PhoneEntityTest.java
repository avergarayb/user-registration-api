package com.smartjob.user_registration_api.infrastructure.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneEntityTest {

    @Test
    void testPhoneEntityBuilder() {
        PhoneEntity phone = PhoneEntity.builder()
                .number("123456789")
                .cityCode("1")
                .countryCode("57")
                .build();

        assertEquals("123456789", phone.getNumber());
        assertEquals("1", phone.getCityCode());
        assertEquals("57", phone.getCountryCode());
    }
}
