package com.aifitness.fitnessapp.training.trainingplan.trainingPlanVersion.model;

import com.aifitness.fitnessapp.training.trainingplan.model.TrainingPlan;
import com.aifitness.fitnessapp.training.workout.model.Workout;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
public class TrainingPlanVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TrainingPlan trainingPlan;

    @Column(nullable = false)
    private int version;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "trainingPlanVersion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Workout> workouts;

}
