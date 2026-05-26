package com.hms.backend.controller;
import com.hms.backend.dto.PatientDTO;
import jakarta.validation.Valid;

import com.hms.backend.entity.Patient;
import com.hms.backend.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
@CrossOrigin("*")
public class PatientController {

    private final PatientService service;

    public PatientController(PatientService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Patient save(@Valid @RequestBody PatientDTO dto) {

        Patient patient = new Patient();

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setDisease(dto.getDisease());
        patient.setGender(dto.getGender());

        return service.save(patient);
    }

    // READ ALL
    @GetMapping
    public List<Patient> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Optional<Patient> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Patient update(
            @PathVariable Long id,
            @RequestBody Patient patient) {

        return service.update(id, patient);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "Patient deleted successfully";
    }
}