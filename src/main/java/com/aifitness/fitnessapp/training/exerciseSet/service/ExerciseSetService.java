package com.aifitness.fitnessapp.training.exerciseSet.service;

import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetResponse;
import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetRequest;

public interface ExerciseSetService {

    ExerciseSetResponse addSet(ExerciseSetRequest exerciseSetResponse);
}
