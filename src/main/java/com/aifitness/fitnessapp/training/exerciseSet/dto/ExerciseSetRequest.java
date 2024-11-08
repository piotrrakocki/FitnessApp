package com.aifitness.fitnessapp.training.exerciseSet.dto;

import java.util.Optional;

public record ExerciseSetRequest(
        Long exerciseSessionId,
        double weightUsed,
        int repsCompleted,
        Optional<String> notes
) {
}
