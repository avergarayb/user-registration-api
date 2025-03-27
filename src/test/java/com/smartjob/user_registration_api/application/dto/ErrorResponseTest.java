package com.smartjob.user_registration_api.application.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorResponseTest {

    @Test
    void testErrorResponseCreation() {
        String errorMessage = "Error de validación";
        ErrorResponse response = new ErrorResponse(errorMessage);

        assertEquals(errorMessage, response.getMensaje());
    }

    @Test
    void testErrorResponseSetters() {
        ErrorResponse response = new ErrorResponse();
        String errorMessage = "Nuevo mensaje de error";

        response.setMensaje(errorMessage);

        assertEquals(errorMessage, response.getMensaje());
    }
}
