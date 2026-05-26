package com.hms.backend.service;

import com.hms.backend.entity.Patient;
import com.hms.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository repository;

    private Patient patient1;

    public PatientService(PatientRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Patient save(Patient patient) {
        return repository.save(patient);
    }

    // READ ALL
    public List<Patient> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public Optional<Patient> getById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public Patient update(Long id, Patient patientDetails) {

        Patient patient = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(patientDetails.getName());
        patient.setAge(patientDetails.getAge());
        patient.setDisease(patientDetails.getDisease());
        patient.setGender(patientDetails.getGender());

        return repository.save(patient);
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}