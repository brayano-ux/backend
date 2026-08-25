package com.mbemnova.epidemie.repository;

import com.mbemnova.epidemie.entity.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HopitalRepository extends JpaRepository<Hopital, Long> {
    List<Hopital> findByLocalisation(String localisation);
}