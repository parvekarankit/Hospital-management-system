package com.hms.backend.service;

import com.hms.backend.dto.AppointmentDTO;
import com.hms.backend.entity.Appointment;
import com.hms.backend.entity.Doctor;
import com.hms.backend.entity.Patient;
import com.hms.backend.repository.AppointmentRepository;
import com.hms.backend.repository.DoctorRepository;
import com.hms.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository,
            PatientRepository patientRepository,
            DoctorRepository doctorRepository) {

        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    // SCHEDULE APPOINTMENT
    public Appointment createAppointment(AppointmentDTO dto) {

        boolean alreadyBooked =
                appointmentRepository
                        .existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                                dto.getDoctorId(),
                                dto.getAppointmentDate(),
                                dto.getAppointmentTime()
                        );

        if (alreadyBooked) {
            throw new RuntimeException(
                    "Doctor is not available at this time");
        }

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() ->
                        new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() ->
                        new RuntimeException("Doctor not found"));

        Appointment appointment = new Appointment();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus("Scheduled");

        return appointmentRepository.save(appointment);
    }

    // GET ALL
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // RESCHEDULE APPOINTMENT
    public Appointment rescheduleAppointment(
            Long appointmentId,
            AppointmentDTO dto) {

        Appointment appointment =
                appointmentRepository.findById(appointmentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Appointment not found"));

        boolean alreadyBooked =
                appointmentRepository
                        .existsByDoctorIdAndAppointmentDateAndAppointmentTime(
                                dto.getDoctorId(),
                                dto.getAppointmentDate(),
                                dto.getAppointmentTime()
                        );

        if (alreadyBooked) {
            throw new RuntimeException(
                    "Doctor already booked at this slot");
        }

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setStatus("Rescheduled");

        return appointmentRepository.save(appointment);
    }

    // DELETE
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}