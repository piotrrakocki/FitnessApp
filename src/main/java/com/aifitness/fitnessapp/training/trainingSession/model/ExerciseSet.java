package com.aifitness.fitnessapp.training.trainingSession.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExerciseSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ExerciseSession exerciseSession;

    @Column(nullable = false)
    private double weightUsed;

    @Column(nullable = false)
    private int repsCompleted;

    @Column(length = 500)
    private String notes;
}
