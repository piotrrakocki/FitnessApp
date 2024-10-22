package com.aifitness.fitnessapp.training.trainingplan.controller;

import com.aifitness.fitnessapp.training.trainingplan.dto.TrainingPlanResponse;
import com.aifitness.fitnessapp.training.trainingplan.service.TrainingPlanService;
import com.aifitness.fitnessapp.user.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/training-plans")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;

    @PostMapping("/create")
    public ResponseEntity<TrainingPlanResponse> createTrainingPlan(@AuthenticationPrincipal AppUser appUser, @RequestBody String planName) {
            return ResponseEntity.ok(trainingPlanService.createTrainingPlan(appUser, planName));
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateTrainingPlan(
            @AuthenticationPrincipal AppUser appUser,
            @RequestParam String level,
            @RequestParam String experience,
            @RequestParam String lifestyle,
            @RequestParam int sessionsPerWeek
    ) {
        return ResponseEntity.ok(trainingPlanService.generateTrainingPlan(appUser, level, experience, lifestyle, sessionsPerWeek));
    }

    @GetMapping("/get")
    public ResponseEntity<List<TrainingPlanResponse>> getTrainingPlans(@AuthenticationPrincipal AppUser appUser) {
        return ResponseEntity.ok(trainingPlanService.getTrainingPlans(appUser));
    }

    @GetMapping("/get-newest/{trainingPlanId}")
    public ResponseEntity<TrainingPlanResponse> getNewestTrainingPlan(@AuthenticationPrincipal AppUser appUser, @PathVariable Long trainingPlanId) {
        return ResponseEntity.ok(trainingPlanService.getNewestTrainingPlan(appUser, trainingPlanId));
    }
}
