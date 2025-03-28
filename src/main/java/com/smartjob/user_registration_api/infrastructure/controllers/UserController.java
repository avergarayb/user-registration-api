package com.smartjob.user_registration_api.infrastructure.controllers;

import com.smartjob.user_registration_api.application.dto.ErrorResponse;
import com.smartjob.user_registration_api.application.dto.UserRequest;
import com.smartjob.user_registration_api.application.dto.UserResponse;
import com.smartjob.user_registration_api.domain.exceptions.UserAlreadyExistsException;
import com.smartjob.user_registration_api.domain.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "API para gestión de usuarios")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Registrar nuevo usuario", description = "Crea un nuevo usuario en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario creado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "El correo ya está registrado",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest request) {
        try {
            UserResponse response = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(e.getMessage()));
        }
    }
}
