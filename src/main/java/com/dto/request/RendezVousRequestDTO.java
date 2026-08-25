package com.mbemnova.epidemie.dto.request;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public record RendezVousRequestDTO(
        @NotNull(message = "Le patient est obligatoire")
        Long patientId,

        @NotNull(message = "L'hôpital est obligatoire")
        Long hopitalId,

        @NotNull(message = "La date est obligatoire")
        @FutureOrPresent(message = "La date ne doit pas être dans le passé")
        LocalDate date,

        @NotNull(message = "L'heure est obligatoire")
        LocalTime heure
) {}