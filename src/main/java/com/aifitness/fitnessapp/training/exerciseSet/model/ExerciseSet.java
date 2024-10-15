package com.aifitness.fitnessapp.training.exerciseSet.model;

import com.aifitness.fitnessapp.training.exerciseSession.model.ExerciseSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
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

    public ExerciseSet(ExerciseSession exerciseSession, double weightUsed, int repsCompleted, String notes) {
        this.exerciseSession = exerciseSession;
        this.weightUsed = weightUsed;
        this.repsCompleted = repsCompleted;
        this.notes = notes;
    }
}
