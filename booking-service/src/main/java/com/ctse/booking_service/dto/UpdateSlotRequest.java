package com.ctse.booking_service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateSlotRequest {
    private String doctorName;
    private LocalDate date;
    private String startTime;
    private String endTime;
}
