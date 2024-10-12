package com.aifitness.fitnessapp.training.workout.repository;

import com.aifitness.fitnessapp.training.workout.model.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    List<Workout> findByTrainingPlanVersionId(Long userId);
}
