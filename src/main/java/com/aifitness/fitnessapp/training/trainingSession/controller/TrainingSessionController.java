package com.aifitness.fitnessapp.training.trainingSession.controller;

import com.aifitness.fitnessapp.training.trainingSession.dto.TrainingSessionResponse;
import com.aifitness.fitnessapp.training.trainingSession.service.TrainingSessionService;
import com.aifitness.fitnessapp.user.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/training-sessions")
@RequiredArgsConstructor
public class TrainingSessionController {

    private final TrainingSessionService trainingSessionService;

    @PostMapping("/start/{workoutId}")
    public ResponseEntity<TrainingSessionResponse> startTrainingSession(@AuthenticationPrincipal AppUser appUser, @PathVariable Long workoutId) {
        Long userId = appUser.getId();

        return ResponseEntity.ok(trainingSessionService.startTrainingSession(userId, workoutId));
    }
}
