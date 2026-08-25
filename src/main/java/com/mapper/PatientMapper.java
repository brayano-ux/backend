package com.mbemnova.epidemie.mapper;

import com.mbemnova.epidemie.dto.request.PatientRequestDTO;
import com.mbemnova.epidemie.dto.response.PatientResponseDTO;
import com.mbemnova.epidemie.entity.Patient;

public class PatientMapper {

    public static Patient toEntity(PatientRequestDTO dto) {
        return Patient.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .localisation(dto.localisation())
                .build();
    }

    public static PatientResponseDTO toDTO(Patient p) {
        return new PatientResponseDTO(
                p.getId(), p.getNom(), p.getPrenom(), p.getLocalisation(),
                p.getDateCreation(), p.getDateDerniereModification()
        );
    }
}