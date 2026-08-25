package com.mbemnova.epidemie.dto.response;

import java.time.LocalDateTime;

public record PatientResponseDTO(
        Long id,
        String nom,
        String prenom,
        String localisation,
        LocalDateTime dateCreation,
        LocalDateTime dateDerniereModification
) {}