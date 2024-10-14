package com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.service;

import com.aifitness.fitnessapp.exceptions.ExerciseSessionNotFoundException;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.dto.ExerciseSetRequest;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.dto.ExerciseSetResponse;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.model.ExerciseSet;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.repository.ExerciseSetRepository;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.model.ExerciseSession;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.repository.ExerciseSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseSetServiceImpl implements ExerciseSetService {

    private final ExerciseSetRepository exerciseSetRepository;
    private final ExerciseSessionRepository exerciseSessionRepository;

    @Override
    public ExerciseSetResponse addSet(ExerciseSetRequest exerciseSetrequest) {
        ExerciseSession exerciseSession = exerciseSessionRepository.findById(exerciseSetrequest.exerciseSessionId())
                .orElseThrow(() -> new ExerciseSessionNotFoundException("Exercise session with exerciseSessionId: " + exerciseSetrequest.exerciseSessionId() + " not found."));

        ExerciseSet exerciseSet = new ExerciseSet(
                exerciseSession,
                exerciseSetrequest.weightUsed(),
                exerciseSetrequest.repsCompleted(),
                exerciseSetrequest.notes().orElse(null)
        );

        exerciseSetRepository.save(exerciseSet);


        return new ExerciseSetResponse(
                exerciseSet.getId(),
                exerciseSetrequest.exerciseSessionId(),
                exerciseSetrequest.weightUsed(),
                exerciseSetrequest.repsCompleted(),
                exerciseSetrequest.notes()
        );
    }
}
