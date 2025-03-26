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
    private String name;
    private String email;
    private List<PhoneResponse> phones;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean active;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneResponse {
        private String number;
        private String cityCode;
        private String countryCode;
    }
}
