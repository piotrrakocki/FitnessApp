package com.aifitness.fitnessapp.training.workout.model;

import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.model.TrainingPlanVersion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "training_plan_version_id", nullable = false)
    private TrainingPlanVersion trainingPlanVersion;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises;
}
