package com.aifitness.fitnessapp.training.workout.service;

import com.aifitness.fitnessapp.training.workout.dto.WorkoutResponse;
import com.aifitness.fitnessapp.training.workout.model.Workout;

public interface WorkoutService {

    WorkoutResponse addWorkoutToTrainingPlan(Long trainingPlanId, Workout workout);
}
