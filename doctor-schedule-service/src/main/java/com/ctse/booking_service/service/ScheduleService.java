package com.hospital.doctorservice.service;

import com.hospital.doctorservice.model.Schedule;
import com.hospital.doctorservice.model.SlotStatus;
import com.hospital.doctorservice.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule createSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getAvailableSlots() {
        return scheduleRepository.findByStatus(SlotStatus.AVAILABLE);
    }

    public Schedule updateSchedule(UUID slotId, Schedule updatedSchedule) {

        Schedule schedule = scheduleRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        schedule.setDate(updatedSchedule.getDate());
        schedule.setStartTime(updatedSchedule.getStartTime());
        schedule.setEndTime(updatedSchedule.getEndTime());

        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(UUID slotId) {
        scheduleRepository.deleteById(slotId);
    }

    public Schedule bookSlot(UUID slotId) {

        Schedule schedule = scheduleRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        schedule.setStatus(SlotStatus.BOOKED);

        return scheduleRepository.save(schedule);
    }

    public Schedule releaseSlot(UUID slotId) {

        Schedule schedule = scheduleRepository.findById(slotId)
                .orElseThrow(() -> new RuntimeException("Slot not found"));

        schedule.setStatus(SlotStatus.AVAILABLE);

        return scheduleRepository.save(schedule);
    }
}