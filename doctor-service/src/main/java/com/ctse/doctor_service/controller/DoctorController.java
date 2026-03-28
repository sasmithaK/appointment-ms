package com.ctse.doctor_service.controller;

import com.ctse.doctor_service.model.Doctor;
import com.ctse.doctor_service.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.createDoctor(doctor);
    }

    @GetMapping
    public List<Doctor> getDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorId}")
    public Doctor getDoctor(@PathVariable String doctorId) {
        return doctorService.getDoctor(doctorId);
    }
}