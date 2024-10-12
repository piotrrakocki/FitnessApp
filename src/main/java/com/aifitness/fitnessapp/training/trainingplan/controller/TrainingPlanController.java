package com.aifitness.fitnessapp.training.trainingplan.controller;

import com.aifitness.fitnessapp.training.trainingplan.dto.CreateTrainingPlanRequest;
import com.aifitness.fitnessapp.training.trainingplan.dto.TrainingPlanResponse;
import com.aifitness.fitnessapp.training.trainingplan.service.TrainingPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/training-plans")
@RequiredArgsConstructor
public class TrainingPlanController {

    private final TrainingPlanService trainingPlanService;

    @PostMapping("/create")
    public ResponseEntity<TrainingPlanResponse> createTrainingPlan(@RequestBody CreateTrainingPlanRequest request) {
            return ResponseEntity.ok(trainingPlanService.createTrainingPlan(request.userId(), request.planName()));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<TrainingPlanResponse>> getTrainingPlansByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(trainingPlanService.getTrainingPlansByUserId(userId));
    }
}
