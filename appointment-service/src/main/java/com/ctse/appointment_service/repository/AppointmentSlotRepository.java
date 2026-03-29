package com.ctse.appointment_service.repository;

import com.ctse.appointment_service.model.AppointmentSlot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentSlotRepository extends MongoRepository<AppointmentSlot, String> {

    List<AppointmentSlot> findByStatus(String status);

    List<AppointmentSlot> findByStatusAndDate(String status, LocalDate date);
}
