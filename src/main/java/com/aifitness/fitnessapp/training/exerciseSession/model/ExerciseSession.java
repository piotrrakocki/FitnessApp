package com.aifitness.fitnessapp.training.exerciseSession.model;

import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import com.aifitness.fitnessapp.training.exerciseSet.model.ExerciseSet;
import com.aifitness.fitnessapp.training.trainingSession.model.TrainingSession;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class ExerciseSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TrainingSession trainingSession;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Exercise exercise;

    @OneToMany(mappedBy = "exerciseSession", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSet> sets = new ArrayList<>();

    private String notes;
}
