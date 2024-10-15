package com.aifitness.fitnessapp.training.exerciseSet.dto;

import java.util.Optional;

public record ExerciseSetResponse(
        Long id,
        Long exerciseSession,
        double weightUsed,
        int repsCompleted,
        Optional<String> notes
) {
}
