package com.aifitness.fitnessapp.training.progress.model;

import com.aifitness.fitnessapp.training.exercise.model.Exercise;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;

    private int setsCompleted;

    private int repsCompleted;

    private double weightUsed;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Exercise exercise;
}
