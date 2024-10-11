package com.aifitness.fitnessapp.training.exercise.dto;

public record ExerciseRequest(
        String name,
        int sets,
        int minReps,
        int maxReps
) {
}
