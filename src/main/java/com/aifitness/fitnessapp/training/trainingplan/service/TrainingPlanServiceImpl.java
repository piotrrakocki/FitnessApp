package com.aifitness.fitnessapp.training.trainingplan.service;

import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import com.aifitness.fitnessapp.training.trainingplan.reposiotry.TrainingPlanRepository;
import com.aifitness.fitnessapp.user.model.AppUser;
import com.aifitness.fitnessapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TrainingPlanServiceImpl implements TrainingPlanService {

    private final UserRepository userRepository;
    private final TrainingPlanRepository trainingPlanRepository;

    @Override
    public TrainingPlan createTrainingPlan(Long userId, String planName) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Uzytkownik o tym ID nie istnieje"));


        TrainingPlan trainingPlan = new TrainingPlan();
        trainingPlan.setName(planName);
        trainingPlan.setUser(user);
        trainingPlan.setCreatedAt(LocalDateTime.now());

        return trainingPlanRepository.save(trainingPlan);
    }
}
