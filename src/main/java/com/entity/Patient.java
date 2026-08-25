package com.mbemnova.epidemie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(min = 2, max = 100, message = "Le prénom doit contenir entre 2 et 100 caractères")
    private String prenom;

    @NotBlank(message = "La localisation est obligatoire")
    @Size(min = 2, max = 100, message = "La localisation doit contenir entre 2 et 100 caractères")
    private String localisation;

    private LocalDateTime dateCreation;
    private LocalDateTime dateDerniereModification;

    @PrePersist
    protected void onCreate() {
        dateCreation = LocalDateTime.now();
        dateDerniereModification = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dateDerniereModification = LocalDateTime.now();
    }
}