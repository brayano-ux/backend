package com.mbemnova.epidemie.dto.request;

import jakarta.validation.constraints.*;

public record PatientRequestDTO(
        @NotBlank @Size(min = 2, max = 100)
        String nom,

        @NotBlank @Size(min = 2, max = 100)
        String prenom,

        @NotBlank @Size(min = 2, max = 100)
        String localisation
) {}