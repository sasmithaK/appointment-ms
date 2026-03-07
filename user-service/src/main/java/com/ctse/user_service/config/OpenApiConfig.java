package com.ctse.user_service.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Service API")
                        .version("1.0")
                        .description("API documentation for User Service - Appointment Management System")
                        .contact(new Contact()
                                .name("CTSE Team")
                                .email("support@ctse.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .addServersItem(new Server().url("http://localhost:8081").description("Local server"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT authorization token")));
    }
}
//Swagger UI:

//http://localhost:8081/swagger-ui.html (redirects to Swagger UI)
//http://localhost:8081/swagger-ui/index.html (direct access)
//API Docs JSON:
//http://localhost:8081/v3/api-docs
//Via API Gateway (Port 8080):
//http://localhost:8080/auth/* routes correctly to user-service
//Example: http://localhost:8080/auth/login
