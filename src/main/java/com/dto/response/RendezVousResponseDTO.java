package com.mbemnova.epidemie.dto.response;

import com.mbemnova.epidemie.entity.StatutRendezVous;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record RendezVousResponseDTO(
        Long id,
        Long patientId,
        String patientNomComplet,
        Long hopitalId,
        String hopitalNom,
        LocalDate date,
        LocalTime heure,
        StatutRendezVous statut,
        LocalDateTime dateCreation,
        LocalDateTime dateDerniereModification
) {}