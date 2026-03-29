package com.ctse.patient_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "User entity representing patients, doctors, and admins")
public class User {
    @Id
    @Builder.Default
    @Schema(description = "Unique user identifier", example = "123e4567-e89b-12d3-a456-426614174000")
    private String id = UUID.randomUUID().toString();

    @NotBlank
    @JsonProperty("patientName")
    @Schema(description = "Full name of the user", example = "John Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String patientName;

    @NotBlank
    @JsonProperty("contactNumber")
    @Schema(description = "Contact phone number", example = "+1234567890", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contactNumber;

    @NotBlank
    @JsonProperty("age")
    @Schema(description = "User age", example = "30", requiredMode = Schema.RequiredMode.REQUIRED)
    private String age;


    @Email
    @NotBlank
    @Schema(description = "Email address", example = "user@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @NotBlank
    @Schema(description = "Hashed password", example = "$2a$10$...", requiredMode = Schema.RequiredMode.REQUIRED)
    private String passwordHash;

    @NotBlank
    @Schema(description = "User role", example = "PATIENT", allowableValues = {"PATIENT", "DOCTOR", "ADMIN"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private String role;

    @Builder.Default
    @Schema(description = "Account creation timestamp")
    private Instant createdAt = Instant.now();
}
