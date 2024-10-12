package com.aifitness.fitnessapp.training.workout.controller;

import com.aifitness.fitnessapp.training.workout.dto.WorkoutDetailsResponse;
import com.aifitness.fitnessapp.training.workout.dto.WorkoutResponse;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.training.workout.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/create/{trainingPlan}")
    public ResponseEntity<WorkoutResponse> addWorkoutToTrainingPlan(@PathVariable Long trainingPlan, @RequestBody Workout workout) {
            return ResponseEntity.ok(workoutService.addWorkoutToTrainingPlan(trainingPlan, workout));
    }

    @GetMapping("/get/{trainingPlanId}")
    public ResponseEntity<List<WorkoutDetailsResponse>> getWorkoutsByTrainingPlanId(@PathVariable Long trainingPlanId) {
        return ResponseEntity.ok(workoutService.getWorkoutsByTrainingPlanId(trainingPlanId));
    }
}
