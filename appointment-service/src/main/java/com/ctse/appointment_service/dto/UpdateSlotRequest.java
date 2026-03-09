package com.ctse.appointment_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Request body to update an existing slot (all fields optional)")
public class UpdateSlotRequest {
    @Schema(description = "Doctor or consultant name")
    private String doctorName;
    @Schema(description = "Appointment date")
    private LocalDate date;
    @Schema(description = "Slot start time", example = "09:00")
    private String startTime;
    @Schema(description = "Slot end time", example = "10:00")
    private String endTime;
}
