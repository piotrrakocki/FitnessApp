package com.aifitness.fitnessapp.training.exerciseSession.dto;

public record ExerciseSessionResponse (
        Long id,
        Long trainingSessionId,
        Long ExerciseId
) {
}
