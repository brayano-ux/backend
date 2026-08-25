package com.mbemnova.epidemie.service;

import com.mbemnova.epidemie.dto.request.RendezVousRequestDTO;
import com.mbemnova.epidemie.dto.response.RendezVousResponseDTO;
import com.mbemnova.epidemie.dto.response.StatistiquesDTO;
import com.mbemnova.epidemie.entity.*;
import com.mbemnova.epidemie.exception.*;
import com.mbemnova.epidemie.mapper.RendezVousMapper;
import com.mbemnova.epidemie.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final HopitalRepository hopitalRepository;
    private final PatientRepository patientRepository;

    public RendezVousResponseDTO creer(RendezVousRequestDTO dto) {
        Hopital hopital = hopitalRepository.findById(dto.hopitalId())
                .orElseThrow(() -> new RessourceIntrouvableException("Hôpital introuvable, id=" + dto.hopitalId()));
        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new RessourceIntrouvableException("Patient introuvable, id=" + dto.patientId()));

        long rdvActifs = rendezVousRepository
                .findByHopitalIdAndStatut(hopital.getId(), StatutRendezVous.CONFIRME)
                .size();
        if (rdvActifs >= hopital.getCapacite()) {
            throw new CapaciteAtteinteException(
                    "L'hôpital " + hopital.getNom() + " a atteint sa capacité maximale."
            );
        }

        boolean conflit = rendezVousRepository.existsByHopitalIdAndDateAndHeureAndStatut(
                hopital.getId(), dto.date(), dto.heure(), StatutRendezVous.CONFIRME
        );
        if (conflit) {
            throw new ConflitHoraireException(
                    "Un rendez-vous existe déjà à cette date et heure dans cet hôpital."
            );
        }

        RendezVous rdv = RendezVous.builder()
                .patient(patient)
                .hopital(hopital)
                .date(dto.date())
                .heure(dto.heure())
                .build();

        return RendezVousMapper.toDTO(rendezVousRepository.save(rdv));
    }

    public List<RendezVousResponseDTO> listerTous() {
        return rendezVousRepository.findAll().stream().map(RendezVousMapper::toDTO).toList();
    }

    public RendezVousResponseDTO modifier(Long id, RendezVousRequestDTO dto) {
        RendezVous rdv = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Rendez-vous introuvable, id=" + id));

        Hopital nouvelHopital = hopitalRepository.findById(dto.hopitalId())
                .orElseThrow(() -> new RessourceIntrouvableException("Hôpital introuvable, id=" + dto.hopitalId()));

        // Si on change hôpital, date ou heure, on revérifie capacité + conflit
        boolean changeCreneauOuHopital =
                !rdv.getHopital().getId().equals(dto.hopitalId())
                        || !rdv.getDate().equals(dto.date())
                        || !rdv.getHeure().equals(dto.heure());

        if (changeCreneauOuHopital) {
            long rdvActifs = rendezVousRepository
                    .findByHopitalIdAndStatut(nouvelHopital.getId(), StatutRendezVous.CONFIRME)
                    .stream()
                    .filter(r -> !r.getId().equals(id)) // exclure le rdv qu'on est en train de modifier
                    .count();
            if (rdvActifs >= nouvelHopital.getCapacite()) {
                throw new CapaciteAtteinteException(
                        "L'hôpital " + nouvelHopital.getNom() + " a atteint sa capacité maximale."
                );
            }

            boolean conflit = rendezVousRepository.existsByHopitalIdAndDateAndHeureAndStatut(
                    nouvelHopital.getId(), dto.date(), dto.heure(), StatutRendezVous.CONFIRME
            ) && !rdv.getHopital().getId().equals(nouvelHopital.getId());
            if (conflit) {
                throw new ConflitHoraireException(
                        "Un rendez-vous existe déjà à cette date et heure dans cet hôpital."
                );
            }
        }

        rdv.setHopital(nouvelHopital);
        rdv.setDate(dto.date());
        rdv.setHeure(dto.heure());

        return RendezVousMapper.toDTO(rendezVousRepository.save(rdv));
    }

    public RendezVousResponseDTO annuler(Long id) {
        RendezVous rdv = rendezVousRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Rendez-vous introuvable, id=" + id));
        rdv.setStatut(StatutRendezVous.ANNULE);
        return RendezVousMapper.toDTO(rendezVousRepository.save(rdv));
    }

    public StatistiquesDTO getStatistiques() {
        return new StatistiquesDTO(
                patientRepository.count(),
                hopitalRepository.count(),
                rendezVousRepository.count()
        );
    }
}