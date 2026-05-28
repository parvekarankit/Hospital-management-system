package com.hms.backend.controller;

import com.hms.backend.dto.AppointmentDTO;
import com.hms.backend.entity.Appointment;
import com.hms.backend.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin("*")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public Appointment createAppointment(
            @Valid @RequestBody AppointmentDTO dto) {

        return service.createAppointment(dto);
    }

    // GET ALL
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return service.getAllAppointments();
    }

    // RESCHEDULE
    @PutMapping("/{id}")
    public Appointment rescheduleAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentDTO dto) {

        return service.rescheduleAppointment(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {

        service.deleteAppointment(id);

        return "Appointment deleted successfully";
    }
}