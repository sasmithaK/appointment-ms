package com.hospital.doctorservice.controller;

import com.hospital.doctorservice.model.Schedule;
import com.hospital.doctorservice.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public Schedule createSchedule(@RequestBody Schedule schedule) {
        return scheduleService.createSchedule(schedule);
    }

    @GetMapping
    public List<Schedule> getSchedules() {
        return scheduleService.getAllSchedules();
    }

    @GetMapping("/available")
    public List<Schedule> getAvailableSlots() {
        return scheduleService.getAvailableSlots();
    }

    @PutMapping("/{slotId}")
    public Schedule updateSchedule(@PathVariable UUID slotId,
                                   @RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(slotId, schedule);
    }

    @DeleteMapping("/{slotId}")
    public void deleteSchedule(@PathVariable UUID slotId) {
        scheduleService.deleteSchedule(slotId);
    }

    @PutMapping("/{slotId}/book")
    public Schedule bookSlot(@PathVariable UUID slotId) {
        return scheduleService.bookSlot(slotId);
    }

    @PutMapping("/{slotId}/release")
    public Schedule releaseSlot(@PathVariable UUID slotId) {
        return scheduleService.releaseSlot(slotId);
    }
}