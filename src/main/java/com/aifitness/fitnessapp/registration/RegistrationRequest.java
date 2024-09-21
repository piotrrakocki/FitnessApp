package com.aifitness.fitnessapp.registration;

import com.aifitness.fitnessapp.user.model.Gender;

import java.time.LocalDate;

public record RegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber,
        LocalDate dateOfBirth,
        Gender gender
) {
}
