package com.aifitness.fitnessapp.training.exercise.service;

import com.aifitness.fitnessapp.exceptions.ExerciseNotFoundException;
import com.aifitness.fitnessapp.exceptions.WorkoutNotFoundException;
import com.aifitness.fitnessapp.training.exercise.dto.ExerciseRequest;
import com.aifitness.fitnessapp.training.exercise.dto.ExerciseResponse;
import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import com.aifitness.fitnessapp.training.exercise.repository.ExerciseRepository;
import com.aifitness.fitnessapp.training.trainingSession.service.TrainingSessionService;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.model.TrainingPlanVersion;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.service.TrainingPlanVersionService;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.training.workout.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final TrainingSessionService trainingSessionService;
    private final TrainingPlanVersionService trainingPlanVersionService;

    @Override
    public ExerciseResponse addExercise(Long workoutId, ExerciseRequest exerciseRequest) {
        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout with id: " + workoutId + " not found."));

        Exercise exercise = new Exercise();
        exercise.setName(exerciseRequest.name());
        exercise.setSets(exerciseRequest.sets());
        exercise.setMinReps(exerciseRequest.minReps());
        exercise.setMaxReps(exerciseRequest.maxReps());
        exercise.setWorkout(workout);

        Exercise savedExercise = exerciseRepository.save(exercise);

        return new ExerciseResponse(
                savedExercise.getId(),
                savedExercise.getName(),
                savedExercise.getSets(),
                savedExercise.getMinReps(),
                savedExercise.getMaxReps()
        );
    }

    @Override
    public ExerciseResponse editExercise(Long exerciseId, String name, int sets, int minReps, int maxReps) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise with exerciseId: " + exerciseId + " not found."));

        if (trainingSessionService.isTrainingSessionExist(exercise.getWorkout().getId())) {
            TrainingPlanVersion originalVersion = exercise.getWorkout().getTrainingPlanVersion();
            Long trainingPlanId = originalVersion.getTrainingPlan().getId();
            TrainingPlanVersion newVersion = trainingPlanVersionService.createTrainingPlanVersion(trainingPlanId);

            for (Workout originalWorkout : originalVersion.getWorkouts()) {
                Workout clonedWorkout = new Workout();
                clonedWorkout.setName(originalWorkout.getName());
                clonedWorkout.setDescription(originalWorkout.getDescription());
                clonedWorkout.setTrainingPlanVersion(newVersion);

                List<Exercise> clonedExercises = new ArrayList<>();
                for (Exercise originalExercise : originalWorkout.getExercises()) {
                    Exercise clonedExercise = new Exercise();
                    clonedExercise.setName(originalExercise.getName());
                    clonedExercise.setSets(originalExercise.getSets());
                    clonedExercise.setMinReps(originalExercise.getMinReps());
                    clonedExercise.setMaxReps(originalExercise.getMaxReps());
                    clonedExercise.setWorkout(clonedWorkout);

                    clonedExercises.add(clonedExercise);

                    if (originalExercise.getId().equals(exerciseId)) {
                        clonedExercise.setName(name);
                        clonedExercise.setSets(sets);
                        clonedExercise.setMinReps(minReps);
                        clonedExercise.setMaxReps(maxReps);
                    }
                }
                clonedWorkout.setExercises(clonedExercises);

                workoutRepository.save(clonedWorkout);
            }

        } else {
            boolean hasChange = false;

            if (!Objects.equals(exercise.getName(), name)) {
                hasChange = true;
                exercise.setName(name);
            }

            if (exercise.getSets() != sets) {
                hasChange = true;
                exercise.setSets(sets);
            }

            if (exercise.getMinReps() != minReps) {
                hasChange = true;
                exercise.setMinReps(minReps);
            }

            if (exercise.getMaxReps() != maxReps) {
                hasChange = true;
                exercise.setMaxReps(maxReps);
            }

            if (hasChange) {
                exerciseRepository.save(exercise);
            }
        }
        return new ExerciseResponse(
                exerciseId,
                name,
                sets,
                minReps,
                maxReps
        );
    }
}