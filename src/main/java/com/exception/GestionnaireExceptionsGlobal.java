package com.mbemnova.epidemie.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GestionnaireExceptionsGlobal {

    @ExceptionHandler(RessourceIntrouvableException.class)
    public ResponseEntity<ErreurReponse> gererIntrouvable(RessourceIntrouvableException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErreurReponse(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(CapaciteAtteinteException.class)
    public ResponseEntity<ErreurReponse> gererCapacite(CapaciteAtteinteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErreurReponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(ConflitHoraireException.class)
    public ResponseEntity<ErreurReponse> gererConflit(ConflitHoraireException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErreurReponse(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    // Erreurs de validation Bean Validation (@Valid) sur les DTO de requête
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> gererValidation(MethodArgumentNotValidException ex) {
        Map<String, String> erreurs = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                erreurs.put(err.getField(), err.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erreurs);
    }
}