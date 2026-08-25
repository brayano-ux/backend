package com.mbemnova.epidemie.service;

import com.mbemnova.epidemie.dto.request.HopitalRequestDTO;
import com.mbemnova.epidemie.dto.response.HopitalResponseDTO;
import com.mbemnova.epidemie.entity.Hopital;
import com.mbemnova.epidemie.entity.StatutRendezVous;
import com.mbemnova.epidemie.exception.RessourceIntrouvableException;
import com.mbemnova.epidemie.mapper.HopitalMapper;
import com.mbemnova.epidemie.repository.HopitalRepository;
import com.mbemnova.epidemie.repository.PatientRepository;
import com.mbemnova.epidemie.repository.RendezVousRepository;
import com.mbemnova.epidemie.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HopitalService {

    private final HopitalRepository hopitalRepository;
    private final RendezVousRepository rendezVousRepository;
    private final PatientRepository patientRepository;

    public HopitalResponseDTO creer(HopitalRequestDTO dto) {
        Hopital h = HopitalMapper.toEntity(dto);
        return HopitalMapper.toDTO(hopitalRepository.save(h));
    }

    public List<HopitalResponseDTO> listerTous() {
        return hopitalRepository.findAll().stream().map(HopitalMapper::toDTO).toList();
    }

    public HopitalResponseDTO recupererParId(Long id) {
        Hopital h = hopitalRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Hôpital introuvable, id=" + id));
        return HopitalMapper.toDTO(h);
    }

    public List<HopitalResponseDTO> hopitauxDisponibles(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RessourceIntrouvableException("Patient introuvable, id=" + patientId));

        List<Hopital> nonPleins = hopitalRepository.findAll().stream()
                .filter(h -> !estPlein(h))
                .toList();

        List<Hopital> memeLocalisation = nonPleins.stream()
                .filter(h -> h.getLocalisation().equalsIgnoreCase(patient.getLocalisation()))
                .toList();

        List<Hopital> resultat = memeLocalisation.isEmpty() ? nonPleins : memeLocalisation;
        return resultat.stream().map(HopitalMapper::toDTO).toList();
    }

    private boolean estPlein(Hopital h) {
        long actifs = rendezVousRepository
                .findByHopitalIdAndStatut(h.getId(), StatutRendezVous.CONFIRME)
                .size();
        return actifs >= h.getCapacite();
    }
}