package com.aifitness.fitnessapp.training.trainingSession.exerciseSession.service;

import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.dto.ExerciseSessionResponse;

public interface ExerciseSessionService {

    ExerciseSessionResponse startExerciseSession(Long trainingSessionId, Long exerciseId);

}
