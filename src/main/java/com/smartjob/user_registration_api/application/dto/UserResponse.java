package com.smartjob.user_registration_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Schema(description = "DTO con la respuesta del usuario registrado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "ID único del usuario", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;
    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez")
    private String name;
    @Schema(description = "Correo electrónico", example = "juan@example.com")
    private String email;
    @Schema(description = "Lista de teléfonos asociados al usuario")
    private List<PhoneResponse> phones;
    @Schema(description = "Fecha de creación del usuario")
    private LocalDateTime created;
    @Schema(description = "Fecha de la última actualización de usuario")
    private LocalDateTime modified;
    @Schema(description = "Fecha del último ingreso")
    private LocalDateTime lastLogin;
    @Schema(description = "Token de acceso de la API (UUID")
    private String token;
    @Schema(description = "Estado del usuario (activo/inactivo)")
    private boolean active;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneResponse {
        @Schema(description = "Número de celular del usuario", example = "123456789")
        private String number;
        @Schema(description = "Código de ciudad", example = "11")
        private String cityCode;
        @Schema(description = "Código de país", example = "57")
        private String countryCode;
    }
}
