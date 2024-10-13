package com.aifitness.fitnessapp.training.trainingSession.exerciseSession.dto;

public record ExerciseSessionResponse (
        Long id,
        Long trainingSessionId,
        Long ExerciseId
) {
}
