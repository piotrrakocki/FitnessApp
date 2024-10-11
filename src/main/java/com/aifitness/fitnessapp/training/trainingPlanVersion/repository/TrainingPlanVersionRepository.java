package com.aifitness.fitnessapp.training.trainingPlanVersion.repository;


import com.aifitness.fitnessapp.training.trainingPlanVersion.model.TrainingPlanVersion;
import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingPlanVersionRepository extends JpaRepository<TrainingPlanVersion, Long> {

    int countByTrainingPlan(TrainingPlan trainingPlan);

    @Query("SELECT v FROM TrainingPlanVersion v " +
            "WHERE v.trainingPlan.id = :trainingPlanId " +
            "ORDER BY v.version DESC")
    Optional<TrainingPlanVersion> findLatestVersionByTrainingPlanId(Long trainingPlanId);
}
