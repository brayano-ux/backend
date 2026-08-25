package com.mbemnova.epidemie.dto.response;

import java.time.LocalDateTime;

public record HopitalResponseDTO(
        Long id,
        String nom,
        String localisation,
        Integer capacite,
        LocalDateTime dateCreation,
        LocalDateTime dateDerniereModification
) {}