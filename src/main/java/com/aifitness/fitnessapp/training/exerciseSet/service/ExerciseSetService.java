package com.aifitness.fitnessapp.training.exerciseSet.service;

import com.aifitness.fitnessapp.training.exerciseSet.dto.EditExerciseSetResponse;
import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetRequest;
import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetResponse;

public interface ExerciseSetService {

    ExerciseSetResponse addSet(ExerciseSetRequest exerciseSetResponse);

    EditExerciseSetResponse editSet(Long exerciseSetId, double weightUsed, int repsCompleted, String notes);
}
