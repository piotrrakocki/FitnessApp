package com.aifitness.fitnessapp.training.trainingSession.controller;

import com.aifitness.fitnessapp.training.trainingSession.dto.TrainingSessionResponse;
import com.aifitness.fitnessapp.training.trainingSession.model.Status;
import com.aifitness.fitnessapp.training.trainingSession.service.TrainingSessionService;
import com.aifitness.fitnessapp.user.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/training-sessions")
@RequiredArgsConstructor
public class TrainingSessionController {

    private final TrainingSessionService trainingSessionService;

    @PostMapping("/start/{workoutId}")
    public ResponseEntity<TrainingSessionResponse> startTrainingSession(@AuthenticationPrincipal AppUser appUser, @PathVariable Long workoutId) {
        return ResponseEntity.ok(trainingSessionService.startTrainingSession(appUser, workoutId));
    }

    @PostMapping("/end/{workoutId}")
    public ResponseEntity<TrainingSessionResponse> endTrainingSession(@PathVariable Long workoutId) {
        return ResponseEntity.ok(trainingSessionService.endTrainingSession(workoutId));
    }

    @GetMapping("/get/{workoutId}")
    public ResponseEntity<List<TrainingSessionResponse>> getTrainingSessions(@AuthenticationPrincipal AppUser appUser, @PathVariable Long workoutId) {
        return ResponseEntity.ok(trainingSessionService.getTrainingSessions(appUser, workoutId));
    }

    @GetMapping("/get/{workoutId}/status")
    public ResponseEntity<List<TrainingSessionResponse>> getTrainingSessions(@AuthenticationPrincipal AppUser appUser, @RequestParam Status status, @PathVariable Long workoutId) {
        return ResponseEntity.ok(trainingSessionService.getTrainingSession(appUser, status, workoutId));
    }
}
