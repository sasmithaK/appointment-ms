package com.ctse.booking_service.controller;

import com.ctse.booking_service.dto.CreateSlotRequest;
import com.ctse.booking_service.dto.UpdateSlotRequest;
import com.ctse.booking_service.model.AppointmentSlot;
import com.ctse.booking_service.service.AppointmentSlotService;
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
public class SlotController {

    private final AppointmentSlotService slotService;

    public SlotController(AppointmentSlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<AppointmentSlot> createSlot(@RequestBody CreateSlotRequest request) {
        AppointmentSlot created = slotService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/available")
    public ResponseEntity<List<AppointmentSlot>> getAvailableSlots(
            @RequestParam(required = false) LocalDate date) {
        List<AppointmentSlot> slots = slotService.getAvailableSlots(date);
        return ResponseEntity.ok(slots);
    }

    @PutMapping("/{slotId}")
    public ResponseEntity<AppointmentSlot> updateSlot(
            @PathVariable String slotId,
            @RequestBody UpdateSlotRequest request) {
        AppointmentSlot updated = slotService.update(slotId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{slotId}")
    public ResponseEntity<Void> deleteSlot(@PathVariable String slotId) {
        slotService.delete(slotId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{slotId}/book")
    public ResponseEntity<AppointmentSlot> bookSlot(@PathVariable String slotId) {
        AppointmentSlot slot = slotService.book(slotId);
        return ResponseEntity.ok(slot);
    }

    @PutMapping("/{slotId}/release")
    public ResponseEntity<AppointmentSlot> releaseSlot(@PathVariable String slotId) {
        AppointmentSlot slot = slotService.release(slotId);
        return ResponseEntity.ok(slot);
    }
}
