package com.ctse.appointment_service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Appointment slot document stored in MongoDB (collection: appointment_slots).
 * Maps from the schema: slot_id, doctor_name, date, start_time, end_time, status, created_at.
 */
@Document(collection = "appointment_slots")
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Schema(description = "Appointment slot with doctor, date, time range, and status (AVAILABLE/BOOKED)")
public class AppointmentSlot {

    @Id
    @Schema(description = "Unique slot identifier")
    private String id;

    /** Consultant / doctor name */
    @Schema(description = "Doctor or consultant name", example = "Dr. Smith", requiredMode = Schema.RequiredMode.REQUIRED)
    private String doctorName;

    /** Appointment date */
    @Schema(description = "Appointment date", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate date;

    /** Slot start time (e.g. "09:00") */
    @Schema(description = "Slot start time", example = "09:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startTime;

    /** Slot end time (e.g. "10:00") */
    @Schema(description = "Slot end time", example = "10:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endTime;

    /** AVAILABLE or BOOKED */
    @Schema(description = "Slot status", allowableValues = {"AVAILABLE", "BOOKED"})
    private String status;

    /** Created timestamp */
    @Schema(description = "Creation timestamp")
    private Instant createdAt;

    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_BOOKED = "BOOKED";
}
