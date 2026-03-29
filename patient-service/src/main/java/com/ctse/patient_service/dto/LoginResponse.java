package com.ctse.patient_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Login response containing user details and JWT token")
public class LoginResponse {
    @Schema(description = "Response message", example = "Login successful")
    private String message;
    
    @Schema(description = "User ID", example = "123e4567-e89b-12d3-a456-426614174000")
    private String userId;
    
    @Schema(description = "User email address", example = "user@example.com")
    private String email;
    
    @Schema(description = "User role", example = "PATIENT", allowableValues = {"PATIENT", "DOCTOR", "ADMIN"})
    private String role;
    
    @Schema(description = "JWT authentication token")
    private String token;
}
