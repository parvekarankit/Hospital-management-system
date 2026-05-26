package com.hms.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class DoctorDTO {

    @NotBlank(message = "Doctor name is required")
    private String name;

    private String specialization;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
