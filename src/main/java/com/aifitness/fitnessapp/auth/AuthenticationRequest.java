package com.aifitness.fitnessapp.auth;

public record AuthenticationRequest(
        String email,
        String password
) {
}
