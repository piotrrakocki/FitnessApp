package com.aifitness.fitnessapp.training.exercise.dto;

public record ExerciseResponse(
        Long id,
        String name,
        int sets,
        int minReps,
        int maxReps
) {
}
