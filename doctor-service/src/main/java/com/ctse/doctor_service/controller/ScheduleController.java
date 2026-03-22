package com.ctse.doctor_service.controller;

import com.ctse.doctor_service.model.Schedule;
import com.ctse.doctor_service.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Schedule updateSchedule(@PathVariable String slotId,
                                   @RequestBody Schedule schedule) {
        return scheduleService.updateSchedule(slotId, schedule);
    }

    @DeleteMapping("/{slotId}")
    public void deleteSchedule(@PathVariable String slotId) {
        scheduleService.deleteSchedule(slotId);
    }

    @PutMapping("/{slotId}/book")
    public Schedule bookSlot(@PathVariable String slotId) {
        return scheduleService.bookSlot(slotId);
    }

    @PutMapping("/{slotId}/release")
    public Schedule releaseSlot(@PathVariable String slotId) {
        return scheduleService.releaseSlot(slotId);
    }
}