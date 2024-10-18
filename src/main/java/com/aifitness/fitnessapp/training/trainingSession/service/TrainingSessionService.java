package com.aifitness.fitnessapp.training.trainingSession.service;

import com.aifitness.fitnessapp.training.trainingSession.dto.TrainingSessionResponse;
import com.aifitness.fitnessapp.training.trainingSession.model.Status;
import com.aifitness.fitnessapp.user.model.AppUser;

import java.util.List;

public interface TrainingSessionService {

    TrainingSessionResponse startTrainingSession(AppUser appUser, Long workoutId);

    TrainingSessionResponse endTrainingSession(Long trainingSessionId);

    List<TrainingSessionResponse> getTrainingSessions(AppUser appUser, Long workoutId);

    List<TrainingSessionResponse> getTrainingSession(AppUser appUser, Status status, Long workoutId);

    boolean isTrainingSessionExist(Long workoutId);
}
