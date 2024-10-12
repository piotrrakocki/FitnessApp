package com.aifitness.fitnessapp.training.trainingplan.service;


import com.aifitness.fitnessapp.training.trainingplan.dto.TrainingPlanResponse;

import java.util.List;

public interface TrainingPlanService {

    TrainingPlanResponse createTrainingPlan(Long userId, String planName);

    List<TrainingPlanResponse> getTrainingPlansByUserId(Long userId);

}
