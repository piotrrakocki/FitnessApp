package com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.controller;

import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.dto.ExerciseSetRequest;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.dto.ExerciseSetResponse;
import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.exerciseSet.service.ExerciseSetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/exercise-set")
@RequiredArgsConstructor
public class ExerciseSetController {

    private final ExerciseSetService exerciseSetService;

    @PostMapping("/add")
    public ResponseEntity<ExerciseSetResponse> addSet(@RequestBody ExerciseSetRequest exerciseSetRequest) {
        return ResponseEntity.ok(exerciseSetService.addSet(exerciseSetRequest));
    }


}
