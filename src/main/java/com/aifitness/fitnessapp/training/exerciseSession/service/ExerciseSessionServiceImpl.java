package com.aifitness.fitnessapp.training.exerciseSession.service;

import com.aifitness.fitnessapp.exceptions.ExerciseNotFoundException;
import com.aifitness.fitnessapp.exceptions.TrainingSessionNotFoundException;
import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import com.aifitness.fitnessapp.training.exercise.repository.ExerciseRepository;
import com.aifitness.fitnessapp.training.exerciseSession.dto.ExerciseSessionResponse;
import com.aifitness.fitnessapp.training.exerciseSession.model.ExerciseSession;
import com.aifitness.fitnessapp.training.exerciseSession.repository.ExerciseSessionRepository;
import com.aifitness.fitnessapp.training.trainingSession.model.TrainingSession;
import com.aifitness.fitnessapp.training.trainingSession.repository.TrainingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseSessionServiceImpl implements ExerciseSessionService {

    private final ExerciseSessionRepository exerciseSessionRepository;
    private final ExerciseRepository exerciseRepository;
    private final TrainingSessionRepository trainingSessionRepository;

    @Override
    public ExerciseSessionResponse startExerciseSession(Long trainingSessionId, Long exerciseId) {

        TrainingSession trainingSession = trainingSessionRepository.findById(trainingSessionId)
                .orElseThrow(() -> new TrainingSessionNotFoundException("Training session with trainingSessionId: " + trainingSessionId + " not found."));

        Exercise exercise =exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new ExerciseNotFoundException("Exercise with exerciseId: " + exerciseId + " not found."));

        ExerciseSession exerciseSession = new ExerciseSession();
        exerciseSession.setTrainingSession(trainingSession);
        exerciseSession.setExercise(exercise);

        exerciseSessionRepository.save(exerciseSession);

        return new ExerciseSessionResponse(
                exerciseSession.getId(),
                trainingSessionId,
                exerciseId
        );
    }
}
