package com.ctse.appointment_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Request body to create a new appointment slot")
public class CreateSlotRequest {
    @Schema(description = "Doctor or consultant name", example = "Dr. Smith", requiredMode = Schema.RequiredMode.REQUIRED)
    private String doctorName;
    @Schema(description = "Appointment date", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDate date;
    @Schema(description = "Slot start time", example = "09:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String startTime;
    @Schema(description = "Slot end time", example = "10:00", requiredMode = Schema.RequiredMode.REQUIRED)
    private String endTime;
}
