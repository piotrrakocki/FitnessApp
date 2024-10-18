package com.aifitness.fitnessapp.training.trainingSession.service;

import com.aifitness.fitnessapp.exceptions.TrainingSessionNotFoundException;
import com.aifitness.fitnessapp.exceptions.WorkoutNotFoundException;
import com.aifitness.fitnessapp.training.trainingSession.dto.TrainingSessionResponse;
import com.aifitness.fitnessapp.training.trainingSession.model.Status;
import com.aifitness.fitnessapp.training.trainingSession.model.TrainingSession;
import com.aifitness.fitnessapp.training.trainingSession.repository.TrainingSessionRepository;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.training.workout.repository.WorkoutRepository;
import com.aifitness.fitnessapp.user.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingSessionServiceImpl implements TrainingSessionService {

    private final TrainingSessionRepository trainingSessionRepository;
    private final WorkoutRepository workoutRepository;

    @Override
    public TrainingSessionResponse startTrainingSession(AppUser appUser, Long workoutId) {

        Workout workout = workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout with workoutId: " + workoutId + " not found."));

        TrainingSession trainingSession = new TrainingSession();
        trainingSession.setSessionDate(LocalDateTime.now());
        trainingSession.setUser(appUser);
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

    @Override
    public TrainingSessionResponse endTrainingSession(Long trainingSessionId) {
        TrainingSession trainingSession = trainingSessionRepository.findById(trainingSessionId)
                .orElseThrow(() -> new TrainingSessionNotFoundException("Training session with trainingSessionId: " + trainingSessionId + " not found."));

        trainingSession.setStatus(Status.COMPLETED);

        trainingSessionRepository.save(trainingSession);

        return new TrainingSessionResponse(
                trainingSession.getId(),
                trainingSession.getSessionDate(),
                trainingSessionId,
                trainingSession.getStatus()
        );
    }

    @Override
    public List<TrainingSessionResponse> getTrainingSessions(AppUser appUser, Long workoutId) {
        List<TrainingSession> trainingSessions = trainingSessionRepository.findByUserIdAndWorkoutId(appUser.getId(), workoutId);

        if (trainingSessions.isEmpty()) {
            throw new TrainingSessionNotFoundException("Training session with userId: " + appUser.getId() +
                    " and workoutId: " + workoutId + " not found.");
        }

        return trainingSessions.stream().map(trainingSession ->
                new TrainingSessionResponse(
                        trainingSession.getId(),
                        trainingSession.getSessionDate(),
                        trainingSession.getWorkout().getId(),
                        trainingSession.getStatus()
                )).collect(Collectors.toList());
    }

    @Override
    public List<TrainingSessionResponse> getTrainingSession(AppUser appUser, Status status, Long workoutId) {
        List<TrainingSession> trainingSessions = trainingSessionRepository.findByUserIdAndStatusAndWorkoutId(appUser.getId(), status, workoutId);

        if (trainingSessions.isEmpty()) {
            throw new TrainingSessionNotFoundException("Training session with userId: " + appUser.getId() +
                    " and status: " + status + " and workoutId: " + workoutId + " not found.");
        }

        return trainingSessions.stream().map(trainingSession ->
                new TrainingSessionResponse(
                        trainingSession.getId(),
                        trainingSession.getSessionDate(),
                        trainingSession.getWorkout().getId(),
                        trainingSession.getStatus()
                )).collect(Collectors.toList());
    }

    @Override
    public boolean isTrainingSessionExist(Long workoutId) {
        return trainingSessionRepository.existsByWorkoutId(workoutId);
    }

}
