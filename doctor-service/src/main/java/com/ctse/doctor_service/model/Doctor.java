package com.ctse.doctor_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctors")
public class Doctor {

    @Id
    private String doctorId;
    private String name;
    private String specialization;

    public Doctor() {}

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
}