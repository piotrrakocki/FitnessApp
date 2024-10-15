package com.aifitness.fitnessapp.training.exerciseSession.service;

import com.aifitness.fitnessapp.training.exerciseSession.dto.ExerciseSessionResponse;

public interface ExerciseSessionService {

    ExerciseSessionResponse startExerciseSession(Long trainingSessionId, Long exerciseId);

}
