package com.aifitness.fitnessapp.training.exerciseSet.dto;

public record EditExerciseSetResponse(
        Long id,
        double weightUsed,
        int repsCompleted,String notes

) {
}
