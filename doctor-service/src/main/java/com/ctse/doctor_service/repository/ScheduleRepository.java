package com.ctse.doctor_service.repository;

import com.ctse.doctor_service.model.Schedule;
import com.ctse.doctor_service.model.SlotStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByStatus(SlotStatus status);
    List<Schedule> findByDoctorId(String doctorId);
}