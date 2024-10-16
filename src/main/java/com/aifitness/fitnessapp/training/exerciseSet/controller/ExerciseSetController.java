package com.aifitness.fitnessapp.training.exerciseSet.controller;

import com.aifitness.fitnessapp.training.exerciseSet.dto.EditExerciseSetRequest;
import com.aifitness.fitnessapp.training.exerciseSet.dto.EditExerciseSetResponse;
import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetRequest;
import com.aifitness.fitnessapp.training.exerciseSet.dto.ExerciseSetResponse;
import com.aifitness.fitnessapp.training.exerciseSet.service.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exercise-set")
@RequiredArgsConstructor
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    @PostMapping("/add")
    public ResponseEntity<ExerciseSetResponse> addSet(@RequestBody ExerciseSetRequest exerciseSetRequest) {
        return ResponseEntity.ok(exerciseSetService.addSet(exerciseSetRequest));
    }

    @PutMapping("/edit/{exerciseSetId}")
    public ResponseEntity<EditExerciseSetResponse> editSet(@PathVariable Long exerciseSetId, @RequestBody EditExerciseSetRequest exerciseSetRequest) {
        return ResponseEntity.ok(exerciseSetService.editSet(
                exerciseSetId,
                exerciseSetRequest.weightUsed(),
                exerciseSetRequest.repsCompleted(),
                exerciseSetRequest.notes()));
    }
}
