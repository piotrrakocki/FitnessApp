package com.aifitness.fitnessapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainingPlanVersionNotFoundException extends RuntimeException {
    public TrainingPlanVersionNotFoundException(String message) {
        super(message);
    }
}
