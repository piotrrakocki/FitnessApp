package com.aifitness.fitnessapp.training.exerciseSet.service;

import com.aifitness.fitnessapp.exceptions.ExerciseSessionNotFoundException;
import com.aifitness.fitnessapp.exceptions.ExerciseSetNotFoundException;
import com.aifitness.fitnessapp.training.exerciseSession.model.ExerciseSession;
import com.aifitness.fitnessapp.training.exerciseSession.repository.ExerciseSessionRepository;
import com.aifitness.fitnessapp.training.exerciseSet.dto.EditExerciseSetResponse;
import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetRequest;
import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetResponse;
import com.aifitness.fitnessapp.training.exerciseSet.model.ExerciseSet;
import com.aifitness.fitnessapp.training.exerciseSet.repository.ExerciseSetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    @Override
    public EditExerciseSetResponse editSet(Long exerciseSetId, double weightUsed, int repsCompleted, String notes) {
        ExerciseSet exerciseSet = exerciseSetRepository.findById(exerciseSetId)
                .orElseThrow(() -> new ExerciseSetNotFoundException("ExerciseSet with exerciseSetId: " + exerciseSetId + " not found."));

        boolean hasChange = false;

        if (exerciseSet.getWeightUsed() != weightUsed) {
            exerciseSet.setWeightUsed(weightUsed);
            hasChange = true;
        }

        if (exerciseSet.getRepsCompleted() != repsCompleted) {
            exerciseSet.setRepsCompleted(repsCompleted);
            hasChange = true;
        }

        if (!Objects.equals(exerciseSet.getNotes(), notes)) {
            exerciseSet.setNotes(notes);
            hasChange = true;
        }

        if (hasChange) {
            exerciseSetRepository.save(exerciseSet);
        }

        return new EditExerciseSetResponse(
                exerciseSetId,
                weightUsed,
                repsCompleted,
                notes
        );
    }
}
