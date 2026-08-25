package com.mbemnova.epidemie.exception;

import java.time.LocalDateTime;

public record ErreurReponse(
        int statut,
        String message,
        LocalDateTime horodatage
) {
    public ErreurReponse(int statut, String message) {
        this(statut, message, LocalDateTime.now());
    }
}