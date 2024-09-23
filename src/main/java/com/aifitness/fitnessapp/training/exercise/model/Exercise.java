package com.aifitness.fitnessapp.training.exercise.model;

import com.aifitness.fitnessapp.training.progress.model.Progress;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int sets;

    @Column(nullable = false)
    private int reps;

    @Column(nullable = false)
    private double weight;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Progress> progress;

}
