package com.aifitness.fitnessapp.training.trainingplan.service;

import com.aifitness.fitnessapp.api.service.GPTService;
import com.aifitness.fitnessapp.exceptions.TrainingPlanMappingException;
import com.aifitness.fitnessapp.exceptions.TrainingPlanNotFoundException;
import com.aifitness.fitnessapp.exceptions.UserNotFoundException;
import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import com.aifitness.fitnessapp.training.trainingplan.dto.TrainingPlanResponse;
import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import com.aifitness.fitnessapp.training.trainingplan.reposiotry.TrainingPlanRepository;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.model.TrainingPlanVersion;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.service.TrainingPlanVersionService;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.user.model.AppUser;
import com.aifitness.fitnessapp.user.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingPlanServiceImpl implements TrainingPlanService {

    private final UserRepository userRepository;
    private final GPTService gptService;
    private final TrainingPlanRepository trainingPlanRepository;
    private final TrainingPlanVersionService trainingPlanVersionService;

    @Override
    public TrainingPlanResponse createTrainingPlan(AppUser appUser, String planName) {
        TrainingPlan trainingPlan = new TrainingPlan(planName, appUser, LocalDateTime.now());
        TrainingPlan saveTrainingPlan = trainingPlanRepository.save(trainingPlan);
        trainingPlanVersionService.createTrainingPlanVersion(trainingPlan.getId());

        return mapToResponse(saveTrainingPlan);
    }

    @Override
    public List<TrainingPlanResponse> getTrainingPlans(AppUser appUser) {
        return trainingPlanRepository.findByUserId(appUser.getId())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TrainingPlanResponse getNewestTrainingPlan(AppUser appUser, Long trainingPlanId) {
        TrainingPlan trainingPlan = trainingPlanRepository.findTrainingPlanWithLatestVersion(appUser.getId(), trainingPlanId)
                .orElseThrow(() -> new TrainingPlanNotFoundException("Training Plans with userId " + appUser.getId() + " not found"));

        return mapToResponse(trainingPlan);
    }

    @Override
    public String generateTrainingPlan(AppUser appUser, String level, String experience, String lifestyle, int sessionsPerWeek) {
        String prompt = createPrompt(appUser.getId(), level, experience, lifestyle, sessionsPerWeek);

        String gptResponse = gptService.getChatResponse(prompt).block();

        System.out.println(gptResponse);

        mapJsonToTrainingPlan(gptResponse, appUser.getId());

        return gptResponse;
    }

    private String createPrompt(Long userID, String level, String experience, String lifestyle, int sessionsPerWeek) {
        LocalDateTime now = LocalDateTime.now();
        String promptTemplate = """
            Create training plan for user with the following details:
            - userID: %d
            - Training level: %s
            - Experience: %s
            - Lifestyle: %s
            - Training sessions per week: %d
            
                The JSON object provided below is an **example** to show the structure and variable names. Please use this structure and generate a training plan dynamically based on the user's details. **Do not include code blocks (e.g., ```json) or any other formatting symbols**. Return only the pure JSON object.

                Please ensure that the `minReps` and `maxReps` fields are always integers. For exercises involving time (e.g., plank), use the number of seconds as an integer (e.g., 30 instead of "30 seconds"). Do not use any string values for repetitions or time.

            {
              "name": "Beginner Training Plan",
              "user": {
                "id": %d
              },
              "createdAt": "%s",
              "versions": [
                {
                  "version": 1,
                  "createdAt": "%s",
                  "workouts": [
                    {
                      "name": "Full Body Workout",
                      "description": "A full body workout targeting major muscle groups",
                      "exercises": [
                        {
                          "name": "Squats",
                          "sets": 3,
                          "minReps": 8,
                          "maxReps": 12
                        },
                        {
                          "name": "Push-ups",
                          "sets": 3,
                          "minReps": 8,
                          "maxReps": 12
                        }
                      ]
                    }
                  ]
                }
              ]
            }
                Make sure the output is a valid JSON object without any extra formatting or code blocks.

            """;

        return String.format(promptTemplate, userID, level, experience, lifestyle, sessionsPerWeek, userID, now, now);
    }

    private void mapJsonToTrainingPlan(String gptResponse, Long userId) {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        try {
            TrainingPlan plan = objectMapper.readValue(gptResponse, TrainingPlan.class);

            AppUser user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User with userId: " + userId + " not found."));

            plan.setUser(user);
            associatePlanWithVersionsAndWorkouts(plan);

            trainingPlanRepository.save(plan);

        } catch (IOException e) {
            log.error(String.valueOf(e));
            throw new TrainingPlanMappingException("Error mapping GET response to TrainingPlan", e);
        }
    }

    private void associatePlanWithVersionsAndWorkouts(TrainingPlan plan) {
        if (plan.getVersions() != null) {
            for (TrainingPlanVersion version : plan.getVersions()) {
                version.setTrainingPlan(plan);

                if (version.getWorkouts() != null) {
                    for (Workout workout : version.getWorkouts()) {
                        workout.setTrainingPlanVersion(version);

                        if (workout.getExercises() != null) {
                            for (Exercise exercise : workout.getExercises()) {
                                exercise.setWorkout(workout);
                            }
                        }
                    }
                }
            }
        }
    }

    private TrainingPlanResponse mapToResponse(TrainingPlan trainingPlan) {
        return new TrainingPlanResponse(
                trainingPlan.getId(),
                trainingPlan.getName(),
                trainingPlan.getCreatedAt()
        );
    }
}