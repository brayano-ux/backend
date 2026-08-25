package com.mbemnova.epidemie.dto.response;

public record StatistiquesDTO(
        long nombrePatients,
        long nombreHopitaux,
        long nombreRendezVous
) {}