package com.aifitness.fitnessapp.training.workout.service;

import com.aifitness.fitnessapp.training.workout.dto.WorkoutDetailsResponse;
import com.aifitness.fitnessapp.training.workout.dto.WorkoutResponse;
import com.aifitness.fitnessapp.training.workout.model.Workout;

import java.util.List;

public interface WorkoutService {

    WorkoutResponse addWorkoutToTrainingPlan(Long trainingPlanId, Workout workout);

    WorkoutResponse editWorkout(Long workoutId, String name, String description);

    List<WorkoutDetailsResponse> getWorkoutsByTrainingPlanId(Long userId);
}
