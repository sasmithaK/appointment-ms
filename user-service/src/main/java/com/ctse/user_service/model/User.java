package com.ctse.user_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Instant;
import java.util.UUID;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();

    @NotBlank
    @JsonProperty("patientName")
    private String patientName;

    @NotBlank
    @JsonProperty("contactNumber")
    private String contactNumber;

    @NotBlank
    @JsonProperty("age")
    private String age;


    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String passwordHash;

    @NotBlank
    private String role;

    @Builder.Default
    private Instant createdAt = Instant.now();
}
