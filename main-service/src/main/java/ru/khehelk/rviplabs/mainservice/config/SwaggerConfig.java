package ru.khehelk.rviplabs.mainservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    servers = {
        @Server(url = "${application.gateway-url}/${spring.application.name}")
    }
)
public class SwaggerConfig {}
