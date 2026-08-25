package com.mbemnova.epidemie.repository;

import com.mbemnova.epidemie.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}