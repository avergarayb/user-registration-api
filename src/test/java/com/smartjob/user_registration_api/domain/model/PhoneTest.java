package com.smartjob.user_registration_api.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneTest {

    @Test
    void testNoArgsConstructor() {
        Phone phone = new Phone();

        assertNotNull(phone);
        assertNull(phone.getNumber());
        assertNull(phone.getCityCode());
        assertNull(phone.getCountryCode());
    }

    @Test
    void testAllArgsConstructor() {
        String number = "123456789";
        String cityCode = "1";
        String countryCode = "57";

        Phone phone = new Phone(number, cityCode, countryCode);

        assertEquals(number, phone.getNumber());
        assertEquals(cityCode, phone.getCityCode());
        assertEquals(countryCode, phone.getCountryCode());
    }

    @Test
    void testBuilder() {
        String number = "987654321";
        String cityCode = "2";
        String countryCode = "58";

        Phone phone = Phone.builder()
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build();

        assertEquals(number, phone.getNumber());
        assertEquals(cityCode, phone.getCityCode());
        assertEquals(countryCode, phone.getCountryCode());
    }

    @Test
    void testSettersAndGetters() {
        Phone phone = new Phone();
        String number = "555555555";
        String cityCode = "3";
        String countryCode = "59";

        phone.setNumber(number);
        phone.setCityCode(cityCode);
        phone.setCountryCode(countryCode);

        assertEquals(number, phone.getNumber());
        assertEquals(cityCode, phone.getCityCode());
        assertEquals(countryCode, phone.getCountryCode());
    }

    @Test
    void testEqualsAndHashCode() {
        Phone phone1 = Phone.builder()
                .number("123456789")
                .cityCode("1")
                .countryCode("57")
                .build();

        Phone phone2 = Phone.builder()
                .number("123456789")
                .cityCode("1")
                .countryCode("57")
                .build();

        Phone phone3 = Phone.builder()
                .number("987654321")
                .cityCode("2")
                .countryCode("58")
                .build();

        // Test equals
        assertEquals(phone1, phone2);
        assertNotEquals(phone1, phone3);

        // Test hashCode
        assertEquals(phone1.hashCode(), phone2.hashCode());
        assertNotEquals(phone1.hashCode(), phone3.hashCode());
    }

    @Test
    void testToString() {
        Phone phone = Phone.builder()
                .number("123456789")
                .cityCode("1")
                .countryCode("57")
                .build();

        String phoneString = phone.toString();

        assertTrue(phoneString.contains("number=123456789"));
        assertTrue(phoneString.contains("cityCode=1"));
        assertTrue(phoneString.contains("countryCode=57"));
    }
}
