package com.aifitness.fitnessapp.training.trainingplan.dto;

public record CreateTrainingPlanRequest(
        Long userId,
        String planName
) {
}
