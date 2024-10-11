package com.aifitness.fitnessapp.training.trainingplan.dto;

import java.time.LocalDateTime;

public record TrainingPlanResponse(
        Long userId,
        String name,
        LocalDateTime createdAt
) {
}
