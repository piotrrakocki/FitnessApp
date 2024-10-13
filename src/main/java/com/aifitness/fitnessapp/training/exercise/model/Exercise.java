package com.aifitness.fitnessapp.training.exercise.model;

import com.aifitness.fitnessapp.training.trainingSession.exerciseSession.model.ExerciseSession;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int sets;

    @Column(nullable = false)
    private int minReps;

    @Column(nullable = false)
    private int maxReps;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExerciseSession> exerciseSessions = new ArrayList<>();
}
