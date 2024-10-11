package com.aifitness.fitnessapp.training.trainingPlanVersion.service;

import com.aifitness.fitnessapp.exceptions.TrainingPlanNotFoundException;
import com.aifitness.fitnessapp.training.trainingPlanVersion.model.TrainingPlanVersion;
import com.aifitness.fitnessapp.training.trainingPlanVersion.repository.TrainingPlanVersionRepository;
import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import com.aifitness.fitnessapp.training.trainingplan.reposiotry.TrainingPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrainingPlanVersionImpl implements TrainingPlanVersionService {

    private final TrainingPlanRepository trainingPlanRepository;
    private final TrainingPlanVersionRepository trainingPlanVersionRepository;

    @Override
    public TrainingPlanVersion createTrainingPlanVersion(Long trainingPlanId) {
        TrainingPlan trainingPlan = trainingPlanRepository.findById(trainingPlanId)
                .orElseThrow(() -> new TrainingPlanNotFoundException("Training plan with id: " + trainingPlanId + " does not exist."));

        TrainingPlanVersion newVersion = new TrainingPlanVersion();
        newVersion.setTrainingPlan(trainingPlan);

        int versionNumber = trainingPlanVersionRepository.countByTrainingPlan(trainingPlan) + 1;
        newVersion.setVersion(versionNumber);

        return trainingPlanVersionRepository.save(newVersion);
    }
}
