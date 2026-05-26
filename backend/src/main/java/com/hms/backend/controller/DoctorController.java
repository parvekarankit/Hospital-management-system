package com.hms.backend.controller;
import com.hms.backend.dto.DoctorDTO;
import jakarta.validation.Valid;

import com.hms.backend.entity.Doctor;
import com.hms.backend.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
@CrossOrigin("*")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @PostMapping
    public Doctor save(@Valid @RequestBody DoctorDTO dto) {

        Doctor doctor = new Doctor();

        doctor.setName(dto.getName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setEmail(dto.getEmail());

        return service.save(doctor);
    }

    // READ ALL
    @GetMapping
    public List<Doctor> getAll() {
        return service.getAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Optional<Doctor> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Doctor update(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        return service.update(id, doctor);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "Doctor deleted successfully";
    }
}