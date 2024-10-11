package com.aifitness.fitnessapp.training.exercise.repository;

import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
