package com.mbemnova.epidemie.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hopitaux")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Hopital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nom;

    @NotBlank(message = "La localisation est obligatoire")
    @Size(min = 2, max = 100, message = "La localisation doit contenir entre 2 et 100 caractères")
    private String localisation;

    @NotNull(message = "La capacité est obligatoire")
    @Min(value = 1, message = "La capacité minimale est 1")
    @Max(value = 500, message = "La capacité maximale est 500")
    private Integer capacite;

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