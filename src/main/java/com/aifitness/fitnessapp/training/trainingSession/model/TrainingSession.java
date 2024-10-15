package com.aifitness.fitnessapp.training.trainingSession.model;

import com.aifitness.fitnessapp.training.exerciseSession.model.ExerciseSession;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import com.aifitness.fitnessapp.user.model.AppUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime sessionDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Workout workout;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "trainingSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSession> exerciseSessions = new ArrayList<>();
}
