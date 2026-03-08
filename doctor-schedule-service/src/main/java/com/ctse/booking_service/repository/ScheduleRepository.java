package com.hospital.doctorservice.repository;

import com.hospital.doctorservice.model.Schedule;
import com.hospital.doctorservice.model.SlotStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    List<Schedule> findByStatus(SlotStatus status);

}