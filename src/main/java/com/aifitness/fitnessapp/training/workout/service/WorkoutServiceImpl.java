package com.aifitness.fitnessapp.training.workout.service;

import com.aifitness.fitnessapp.exceptions.TrainingPlanVersionNotFoundException;
import com.aifitness.fitnessapp.exceptions.WorkoutNotFoundException;
import com.aifitness.fitnessapp.training.exercise.dto.ExerciseResponse;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.model.TrainingPlanVersion;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.repository.TrainingPlanVersionRepository;
import com.aifitness.fitnessapp.training.workout.dto.WorkoutResponse;
import com.aifitness.fitnessapp.training.workout.dto.WorkoutDetailsResponse;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.training.workout.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutServiceImpl  implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final TrainingPlanVersionRepository trainingPlanVersionRepository;

    @Override
    public WorkoutResponse addWorkoutToTrainingPlan(Long trainingPlanId, Workout workout) {
        TrainingPlanVersion latestVersionOpt = trainingPlanVersionRepository.findLatestVersionByTrainingPlanId(trainingPlanId)
                .orElseThrow(() -> new TrainingPlanVersionNotFoundException("Latest version for training plan with ID " + trainingPlanId + " not found"));

        workout.setTrainingPlanVersion(latestVersionOpt);
        workoutRepository.save(workout);

        return new WorkoutResponse(
                workout.getId(),
                workout.getName(),
                workout.getDescription()
        );
    }

    @Override
    public WorkoutResponse editWorkout(Long workoutId, String name, String description) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout with workoutId: " + workoutId + " not found."));

        boolean hasChange = false;

        if (!Objects.equals(workout.getName(), name)) {
            hasChange = true;
            workout.setName(name);
        }

        if (!Objects.equals(workout.getDescription(), description)) {
            hasChange = true;
            workout.setDescription(description);
        }

        if (hasChange) {
            workoutRepository.save(workout);
        }

        return new WorkoutResponse(
                workoutId,
                name,
                description
        );
    }

    @Override
    public List<WorkoutDetailsResponse> getWorkoutsByTrainingPlanId(Long trainingPlanId) {
        List<Workout> workouts = workoutRepository.findByTrainingPlanVersionId(trainingPlanId);
        if (workouts.isEmpty()) {
            throw new WorkoutNotFoundException("Workouts with trainingPlanId " + trainingPlanId + " not found.");
        }

        return workouts.stream()
                .map(workout -> new WorkoutDetailsResponse(
                        workout.getId(),
                        workout.getName(),
                        workout.getDescription(),
                        workout.getExercises().stream()
                                .map(exercise -> new ExerciseResponse(
                                        exercise.getId(),
                                        exercise.getName(),
                                        exercise.getSets(),
                                        exercise.getMinReps(),
                                        exercise.getMaxReps()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}
