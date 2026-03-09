package com.ctse.appointment_service.controller;

import com.ctse.appointment_service.dto.CreateSlotRequest;
import com.ctse.appointment_service.dto.UpdateSlotRequest;
import com.ctse.appointment_service.model.AppointmentSlot;
import com.ctse.appointment_service.service.AppointmentSlotService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Appointment slot API. Via API Gateway exposed as /appointments/slots, /appointments/slots/available, etc.
 * - POST /slots — Create slot (Protected Admin)
 * - GET /slots/available — Get available slots (Public)
 * - PUT /slots/{slotId} — Update slot (Protected Admin)
 * - DELETE /slots/{slotId} — Delete slot (Protected Admin)
 * - PUT /slots/{slotId}/book — Mark as booked (Internal - Booking Service)
 * - PUT /slots/{slotId}/release — Release after cancellation (Internal - Booking Service)
 */
@RestController
@RequestMapping("/slots")
@Tag(name = "Slots", description = "Appointment slot API for Doctor & Schedule. Used by other services for Check Slot and Update Slot.")
public class SlotController {

    private final AppointmentSlotService slotService;

    public SlotController(AppointmentSlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    @Operation(summary = "Create slot", description = "Create a new appointment slot (Admin).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Slot created",
                content = @Content(schema = @Schema(implementation = AppointmentSlot.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content)
    })
    public ResponseEntity<AppointmentSlot> createSlot(@RequestBody CreateSlotRequest request) {
        AppointmentSlot created = slotService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/available")
    @Operation(summary = "Get available slots", description = "List available slots, optionally filtered by date. Used by other services for Check Slot.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of available slots",
                content = @Content(schema = @Schema(implementation = AppointmentSlot.class)))
    })
    public ResponseEntity<List<AppointmentSlot>> getAvailableSlots(
            @Parameter(description = "Filter by date (optional)") @RequestParam(required = false) LocalDate date) {
        List<AppointmentSlot> slots = slotService.getAvailableSlots(date);
        return ResponseEntity.ok(slots);
    }

    @PutMapping("/{slotId}")
    @Operation(summary = "Update slot", description = "Update slot details (Admin).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Slot updated",
                content = @Content(schema = @Schema(implementation = AppointmentSlot.class))),
        @ApiResponse(responseCode = "404", description = "Slot not found", content = @Content)
    })
    public ResponseEntity<AppointmentSlot> updateSlot(
            @Parameter(description = "Slot ID") @PathVariable String slotId,
            @RequestBody UpdateSlotRequest request) {
        AppointmentSlot updated = slotService.update(slotId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{slotId}")
    @Operation(summary = "Delete slot", description = "Delete a slot (Admin).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Slot deleted"),
        @ApiResponse(responseCode = "404", description = "Slot not found", content = @Content)
    })
    public ResponseEntity<Void> deleteSlot(
            @Parameter(description = "Slot ID") @PathVariable String slotId) {
        slotService.delete(slotId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{slotId}/book")
    @Operation(summary = "Book slot", description = "Mark slot as booked. Called by Appointment/Booking Service (Update Slot).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Slot booked",
                content = @Content(schema = @Schema(implementation = AppointmentSlot.class))),
        @ApiResponse(responseCode = "404", description = "Slot not found", content = @Content),
        @ApiResponse(responseCode = "409", description = "Slot already booked", content = @Content)
    })
    public ResponseEntity<AppointmentSlot> bookSlot(
            @Parameter(description = "Slot ID") @PathVariable String slotId) {
        AppointmentSlot slot = slotService.book(slotId);
        return ResponseEntity.ok(slot);
    }

    @PutMapping("/{slotId}/release")
    @Operation(summary = "Release slot", description = "Mark slot as available. Called by Appointment/Booking Service after cancellation (Update Slot).")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Slot released",
                content = @Content(schema = @Schema(implementation = AppointmentSlot.class))),
        @ApiResponse(responseCode = "404", description = "Slot not found", content = @Content)
    })
    public ResponseEntity<AppointmentSlot> releaseSlot(
            @Parameter(description = "Slot ID") @PathVariable String slotId) {
        AppointmentSlot slot = slotService.release(slotId);
        return ResponseEntity.ok(slot);
    }
}
