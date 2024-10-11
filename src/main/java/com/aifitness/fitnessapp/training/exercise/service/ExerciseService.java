package com.aifitness.fitnessapp.training.exercise.service;

import com.aifitness.fitnessapp.training.exercise.dto.ExerciseRequest;
import com.aifitness.fitnessapp.training.exercise.dto.ExerciseResponse;

public interface ExerciseService {

    ExerciseResponse addExercise(Long workoutId, ExerciseRequest exerciseRequest);
}