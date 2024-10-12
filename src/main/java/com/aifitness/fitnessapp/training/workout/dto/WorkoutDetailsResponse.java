package com.aifitness.fitnessapp.training.workout.dto;

import com.aifitness.fitnessapp.training.exercise.dto.ExerciseResponse;

import java.util.List;

public record WorkoutDetailsResponse(
        Long id,
        String name,
        String description,
        List<ExerciseResponse> exercises
) {
}
