package com.aifitness.fitnessapp.training.workout.service;

import com.aifitness.fitnessapp.exceptions.TrainingPlanVersionNotFoundException;
import com.aifitness.fitnessapp.training.trainingPlanVersion.model.TrainingPlanVersion;
import com.aifitness.fitnessapp.training.trainingPlanVersion.repository.TrainingPlanVersionRepository;
import com.aifitness.fitnessapp.training.workout.dto.WorkoutResponse;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.training.workout.model.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
