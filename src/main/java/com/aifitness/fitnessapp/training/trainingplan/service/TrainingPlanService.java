package com.aifitness.fitnessapp.training.trainingplan.service;


import com.aifitness.fitnessapp.training.trainingplan.dto.TrainingPlanResponse;

public interface TrainingPlanService {

    TrainingPlanResponse createTrainingPlan(Long userId, String planName);

}
