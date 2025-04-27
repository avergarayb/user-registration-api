package com.smartjob.user_registration_api.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "DTO con la respuesta del usuario registrado")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @Schema(description = "ID único del usuario", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Fecha de creación del usuario")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @Schema(description = "Fecha de la última actualización de usuario")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modified;

    @Schema(description = "Fecha del último ingreso")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLogin;

    @Schema(description = "Token de acceso de la API (UUID")
    private String token;

    @Schema(description = "Estado del usuario (activo/inactivo)")
    private boolean active;

}
