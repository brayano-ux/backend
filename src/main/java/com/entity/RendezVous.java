package com.mbemnova.epidemie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "rendez_vous")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hopital_id", nullable = false)
    private Hopital hopital;

    @NotNull(message = "La date est obligatoire")
    @FutureOrPresent(message = "La date ne doit pas être dans le passé")
    private LocalDate date;

    @NotNull(message = "L'heure est obligatoire")
    private LocalTime heure;

    @Enumerated(EnumType.STRING)
    private StatutRendezVous statut;

    private LocalDateTime dateCreation;
    private LocalDateTime dateDerniereModification;

    @PrePersist
    protected void onCreate() {
        if (statut == null) statut = StatutRendezVous.CONFIRME;
        dateCreation = LocalDateTime.now();
        dateDerniereModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateDerniereModification = LocalDateTime.now();
    }
}