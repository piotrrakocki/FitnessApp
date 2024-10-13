package com.aifitness.fitnessapp.training.trainingSession.exerciseSession.controller;

import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.dto.ExerciseSessionResponse;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.service.ExerciseSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercise-sessions")
@RequiredArgsConstructor
public class ExerciseSessionController {

    private final ExerciseSessionService exerciseSessionService;

    @PostMapping("/{trainingSessionId}/exercises/{exerciseId}")
    public ResponseEntity<ExerciseSessionResponse> startExerciseSession(@PathVariable Long trainingSessionId, @PathVariable Long exerciseId) {
        return ResponseEntity.ok(exerciseSessionService.startExerciseSession(trainingSessionId, exerciseId));
    }
}
