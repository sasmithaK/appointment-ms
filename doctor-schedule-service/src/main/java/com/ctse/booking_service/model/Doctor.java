package com.hospital.doctorservice.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue
    private UUID doctorId;

    private String name;

    private String specialization;

    public Doctor() {}

    public Doctor(String name, String specialization) {
        this.name = name;
        this.specialization = specialization;
    }

    public UUID getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}