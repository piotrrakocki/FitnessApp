package com.aifitness.fitnessapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TrainingPlanMappingException extends RuntimeException {
    public TrainingPlanMappingException(String message, Throwable cause) {
        super(message);
    }
}
