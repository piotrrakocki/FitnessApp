package com.aifitness.fitnessapp.training.trainingplan.reposiotry;

import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingPlanRepository extends JpaRepository<TrainingPlan, Long> {

    List<TrainingPlan> findByUserId(Long userId);

    @Query("SELECT tp FROM TrainingPlan tp " +
            "JOIN FETCH tp.versions tv " +
            "WHERE tp.id = :trainingPlanId AND tp.user.id = :userId " +
            "AND tv.version = (" +
            "   SELECT MAX(tv2.version) FROM TrainingPlanVersion tv2 WHERE tv2.trainingPlan.id = tp.id" +
            ")")
    Optional<TrainingPlan> findTrainingPlanWithLatestVersion(
            @Param("userId") Long userId,
            @Param("trainingPlanId") Long trainingPlanId);
}
