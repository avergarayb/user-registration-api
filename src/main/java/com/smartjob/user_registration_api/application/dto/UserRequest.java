package com.smartjob.user_registration_api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "DTO para creación de usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Schema(description = "Nombre completo del usuario", example = "Juan Pérez", required = true)
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @Schema(description = "Correo electrónico", example = "juan@example.com", required = true)
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String email;

    @Schema(description = "Contraseña (mínimo 8 caracteres, 1 mayúscula, 1 número)",
            example = "Password123", required = true)
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @Schema(description = "Lista de teléfonos asociados al usuario")
    private List<PhoneRequest> phones;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhoneRequest {
        @Schema(description = "Número de celular del usuario", example = "123456789", required = true)
        @NotBlank(message = "El número es obligatorio")
        private String number;

        @Schema(description = "Código de ciudad", example = "11", required = true)
        @NotBlank(message = "El código de ciudad es obligatorio")
        private String cityCode;

        @Schema(description = "Código de país", example = "57", required = true)
        @NotBlank(message = "El código de país es obligatorio")
        private String countryCode;
    }
}
