package com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.service;

import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.dto.ExerciseSetRequest;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.dto.ExerciseSetResponse;

public interface ExerciseSetService {

    ExerciseSetResponse addSet(ExerciseSetRequest exerciseSetResponse);
}
