package com.smartjob.user_registration_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "User Registration API",
                version = "1.0",
                description = "API para registro y gestión de usuarios")
)
public class OpenApiConfig {
}
