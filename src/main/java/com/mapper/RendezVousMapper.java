package com.mbemnova.epidemie.mapper;

import com.mbemnova.epidemie.dto.response.RendezVousResponseDTO;
import com.mbemnova.epidemie.entity.RendezVous;

public class RendezVousMapper {

    public static RendezVousResponseDTO toDTO(RendezVous r) {
        return new RendezVousResponseDTO(
                r.getId(),
                r.getPatient().getId(),
                r.getPatient().getNom() + " " + r.getPatient().getPrenom(),
                r.getHopital().getId(),
                r.getHopital().getNom(),
                r.getDate(),
                r.getHeure(),
                r.getStatut(),
                r.getDateCreation(),
                r.getDateDerniereModification()
        );
    }
}