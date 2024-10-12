package com.aifitness.fitnessapp.training.trainingSession.dto;

import com.aifitness.fitnessapp.training.trainingSession.model.Status;

import java.time.LocalDateTime;

public record TrainingSessionResponse(
        Long id,
        LocalDateTime sessionDate,
        Long workoutId,
        Status status
) {
}
