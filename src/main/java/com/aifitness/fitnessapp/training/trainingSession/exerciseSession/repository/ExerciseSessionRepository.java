package com.aifitness.fitnessapp.training.trainingSession.exerciseSession.repository;

import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.model.ExerciseSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseSessionRepository extends JpaRepository<ExerciseSession, Long> {
}
