package com.aifitness.fitnessapp.training.exercise.service;

import com.aifitness.fitnessapp.exceptions.WorkoutNotFoundException;
import com.aifitness.fitnessapp.training.exercise.dto.ExerciseRequest;
import com.aifitness.fitnessapp.training.exercise.dto.ExerciseResponse;
import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import com.aifitness.fitnessapp.training.exercise.repository.ExerciseRepository;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.training.workout.model.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

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
}
