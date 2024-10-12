package com.aifitness.fitnessapp.training.trainingSession.service;

import com.aifitness.fitnessapp.training.trainingSession.dto.TrainingSessionResponse;

public interface TrainingSessionService {

    TrainingSessionResponse startTrainingSession(Long userId, Long workoutId);
}
