package com.aifitness.fitnessapp.training.trainingSession.repository;

import com.aifitness.fitnessapp.training.trainingSession.model.Status;
import com.aifitness.fitnessapp.training.trainingSession.model.TrainingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {

    List<TrainingSession> findByUserIdAndWorkoutId(Long userId, Long workoutId);

    List<TrainingSession> findByUserIdAndStatusAndWorkoutId(Long userId, Status status, Long workoutId);

    boolean existsByWorkoutId(Long workoutId);

}
