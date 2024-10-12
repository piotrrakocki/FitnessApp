package com.aifitness.fitnessapp.training.trainingSession.service;

import com.aifitness.fitnessapp.exceptions.UserNotFoundException;
import com.aifitness.fitnessapp.exceptions.WorkoutNotFoundException;
import com.aifitness.fitnessapp.training.trainingSession.dto.TrainingSessionResponse;
import com.aifitness.fitnessapp.training.trainingSession.model.Status;
import com.aifitness.fitnessapp.training.trainingSession.model.TrainingSession;
import com.aifitness.fitnessapp.training.trainingSession.repository.TrainingSessionRepository;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.training.workout.repository.WorkoutRepository;
import com.aifitness.fitnessapp.user.model.AppUser;
import com.aifitness.fitnessapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TrainingSessionServiceImpl implements TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;
    private final WorkoutRepository workoutRepository;
    private final UserRepository userRepository;

    @Override
    public TrainingSessionResponse startTrainingSession(Long userId, Long workoutId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with userId: " + userId + " not found."));

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout with workoutId: " + workoutId + " not found."));

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setSessionDate(LocalDateTime.now());
        trainingSession.setUser(user);
        trainingSession.setWorkout(workout);
        trainingSession.setStatus(Status.IN_PROGRESS);

        trainingSessionRepository.save(trainingSession);

        return new TrainingSessionResponse(
                trainingSession.getId(),
                trainingSession.getSessionDate(),
                workoutId,
                trainingSession.getStatus()
        );
    }
}
