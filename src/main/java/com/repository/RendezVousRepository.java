package com.mbemnova.epidemie.repository;

import com.mbemnova.epidemie.entity.RendezVous;
import com.mbemnova.epidemie.entity.StatutRendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByHopitalIdAndStatut(Long hopitalId, StatutRendezVous statut);

    boolean existsByHopitalIdAndDateAndHeureAndStatut(
            Long hopitalId, LocalDate date, LocalTime heure, StatutRendezVous statut
    );
}