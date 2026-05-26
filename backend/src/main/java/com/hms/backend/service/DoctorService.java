package com.hms.backend.service;

import com.hms.backend.entity.Doctor;
import com.hms.backend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Doctor save(Doctor doctor) {
        return repository.save(doctor);
    }

    // READ ALL
    public List<Doctor> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public Optional<Doctor> getById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public Doctor update(Long id, Doctor doctorDetails) {

        Doctor doctor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(doctorDetails.getName());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setEmail(doctorDetails.getEmail());

        return repository.save(doctor);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}