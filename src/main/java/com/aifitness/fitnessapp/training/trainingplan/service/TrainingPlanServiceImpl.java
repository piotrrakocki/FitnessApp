package com.aifitness.fitnessapp.training.trainingplan.service;

import com.aifitness.fitnessapp.exceptions.TrainingPlanNotFoundException;
import com.aifitness.fitnessapp.training.trainingplan.dto.TrainingPlanResponse;
import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import com.aifitness.fitnessapp.training.trainingplan.reposiotry.TrainingPlanRepository;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.service.TrainingPlanVersionService;
import com.aifitness.fitnessapp.user.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingPlanServiceImpl implements TrainingPlanService {

    private final TrainingPlanRepository trainingPlanRepository;
    private final TrainingPlanVersionService trainingPlanVersionService;

    @Override
    public TrainingPlanResponse createTrainingPlan(AppUser appUser, String planName) {
        TrainingPlan trainingPlan = new TrainingPlan(planName, appUser, LocalDateTime.now());

        TrainingPlan saveTrainingPlan = trainingPlanRepository.save(trainingPlan);

        trainingPlanVersionService.createTrainingPlanVersion(trainingPlan.getId());

        return new TrainingPlanResponse(
                saveTrainingPlan.getId(),
                saveTrainingPlan.getName(),
                saveTrainingPlan.getCreatedAt()
        );
    }

    @Override
    public List<TrainingPlanResponse> getTrainingPlans(AppUser appUser) {
        List<TrainingPlan> trainingPlans  = trainingPlanRepository.findByUserId(appUser.getId());
        if (trainingPlans.isEmpty()) {
            throw new TrainingPlanNotFoundException("Training Plans with userId " + appUser.getId() + " not found");
        }

        return trainingPlans.stream().map(plan ->
                new TrainingPlanResponse(
                        plan.getId(),
                        plan.getName(),
                        plan.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public TrainingPlanResponse getNewestTrainingPlan(AppUser appUser, Long trainingPlanId) {
        TrainingPlan trainingPlan = trainingPlanRepository.findTrainingPlanWithLatestVersion(appUser.getId(), trainingPlanId)
                .orElseThrow(() -> new TrainingPlanNotFoundException("Training Plans with userId " + appUser.getId() + " not found"));

        return new TrainingPlanResponse(
                trainingPlan.getId(),
                trainingPlan.getName(),
                trainingPlan.getCreatedAt()
        );
    }
}
