package com.aifitness.fitnessapp.training.trainingplan.reposiotry;

import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {

    List<TrainingPlan> findByUserId(Long userId);
}
