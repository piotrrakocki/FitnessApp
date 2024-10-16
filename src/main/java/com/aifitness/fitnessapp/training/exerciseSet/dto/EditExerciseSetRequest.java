package com.aifitness.fitnessapp.training.exerciseSet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record EditExerciseSetRequest(
        double weightUsed,
        int repsCompleted,
        String notes
) {
}
