package com.aifitness.fitnessapp.training.trainingplan.service;


import com.aifitness.fitnessapp.training.trainingplan.dto.TrainingPlanResponse;
import com.aifitness.fitnessapp.user.model.AppUser;

import java.util.List;

public interface TrainingPlanService {

    TrainingPlanResponse createTrainingPlan(AppUser appUser, String planName);

    List<TrainingPlanResponse> getTrainingPlans(AppUser appUser);

    TrainingPlanResponse getNewestTrainingPlan(AppUser appUser, Long trainingPlanId);

    String generateTrainingPlan(AppUser appUser, String level, String experience, String lifestyle, int sessionPerWeek);

}
