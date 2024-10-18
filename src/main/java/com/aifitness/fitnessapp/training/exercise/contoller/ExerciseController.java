package com.aifitness.fitnessapp.training.exercise.contoller;

import com.aifitness.fitnessapp.training.exercise.dto.ExerciseRequest;
import com.aifitness.fitnessapp.training.exercise.dto.ExerciseResponse;
import com.aifitness.fitnessapp.training.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @PostMapping("/add/{workoutId}")
    public ResponseEntity<ExerciseResponse> addExercise(@PathVariable Long workoutId, @RequestBody ExerciseRequest exerciseRequest) {
        return ResponseEntity.ok(exerciseService.addExercise(workoutId, exerciseRequest));
    }

    @PutMapping("/{exerciseId}")
    public ResponseEntity<ExerciseResponse> updatedExercise(@PathVariable Long exerciseId, @RequestBody ExerciseRequest exerciseRequest) {
        return ResponseEntity.ok(exerciseService.editExercise(
                exerciseId,
                exerciseRequest.name(),
                exerciseRequest.sets(),
                exerciseRequest.minReps(),
                exerciseRequest.maxReps()));
    }
}
