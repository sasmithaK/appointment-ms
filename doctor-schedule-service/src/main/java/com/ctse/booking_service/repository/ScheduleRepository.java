package com.hospital.doctorservice.repository;

import com.hospital.doctorservice.model.Schedule;
import com.hospital.doctorservice.model.SlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {

    List<Schedule> findByStatus(SlotStatus status);

}