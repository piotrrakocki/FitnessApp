package com.aifitness.fitnessapp.training.trainingplan.controller;

public record CreateTrainingPlanRequest(
        Long userId,
        String planName
) {
}
