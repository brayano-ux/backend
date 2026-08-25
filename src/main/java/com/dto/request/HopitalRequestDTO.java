package com.mbemnova.epidemie.dto.request;

import jakarta.validation.constraints.*;

public record HopitalRequestDTO(
        @NotBlank(message = "Le nom est obligatoire")
        @Size(min = 2, max = 100)
        String nom,

        @NotBlank(message = "La localisation est obligatoire")
        @Size(min = 2, max = 100)
        String localisation,

        @NotNull(message = "La capacité est obligatoire")
        @Min(1) @Max(500)
        Integer capacite
) {}