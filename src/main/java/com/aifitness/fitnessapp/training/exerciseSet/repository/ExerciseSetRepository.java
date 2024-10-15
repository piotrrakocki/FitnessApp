package com.aifitness.fitnessapp.training.exerciseSet.repository;

import com.aifitness.fitnessapp.training.exerciseSet.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseSetRepository extends JpaRepository<ExerciseSet, Long> {
}
