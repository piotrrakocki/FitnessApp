package com.aifitness.fitnessapp.training.trainingSession.repository;

import com.aifitness.fitnessapp.training.trainingSession.model.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
}
