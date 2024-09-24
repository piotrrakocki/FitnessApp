package com.aifitness.fitnessapp.training.trainingplan.service;

import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;

public interface TrainingPlanService {

    TrainingPlan createTrainingPlan(Long userId, String planName);

}
