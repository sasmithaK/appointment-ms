package com.ctse.doctor_service.repository;

import com.ctse.doctor_service.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
}