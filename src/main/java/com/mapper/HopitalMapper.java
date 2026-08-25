package com.mbemnova.epidemie.mapper;

import com.mbemnova.epidemie.dto.request.HopitalRequestDTO;
import com.mbemnova.epidemie.dto.response.HopitalResponseDTO;
import com.mbemnova.epidemie.entity.Hopital;

public class HopitalMapper {

    public static Hopital toEntity(HopitalRequestDTO dto) {
        return Hopital.builder()
                .nom(dto.nom())
                .localisation(dto.localisation())
                .capacite(dto.capacite())
                .build();
    }

    public static HopitalResponseDTO toDTO(Hopital h) {
        return new HopitalResponseDTO(
                h.getId(), h.getNom(), h.getLocalisation(), h.getCapacite(),
                h.getDateCreation(), h.getDateDerniereModification()
        );
    }
}