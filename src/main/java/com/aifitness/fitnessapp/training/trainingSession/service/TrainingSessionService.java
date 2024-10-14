package com.aifitness.fitnessapp.training.trainingSession.service;

import com.aifitness.fitnessapp.training.trainingSession.dto.TrainingSessionResponse;
import com.aifitness.fitnessapp.user.model.AppUser;

public interface TrainingSessionService {

    TrainingSessionResponse startTrainingSession(AppUser appUser, Long workoutId);

    TrainingSessionResponse endTrainingSession(Long trainingSessionId);
}
