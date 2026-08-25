package com.mbemnova.epidemie.service;

import com.mbemnova.epidemie.dto.request.PatientRequestDTO;
import com.mbemnova.epidemie.dto.response.PatientResponseDTO;
import com.mbemnova.epidemie.entity.Patient;
import com.mbemnova.epidemie.exception.RessourceIntrouvableException;
import com.mbemnova.epidemie.mapper.PatientMapper;
import com.mbemnova.epidemie.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientResponseDTO creer(PatientRequestDTO dto) {
        Patient p = PatientMapper.toEntity(dto);
        return PatientMapper.toDTO(patientRepository.save(p));
    }

    public List<PatientResponseDTO> listerTous() {
        return patientRepository.findAll().stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO recupererParId(Long id) {
        Patient p = patientRepository.findById(id)
                .orElseThrow(() -> new RessourceIntrouvableException("Patient introuvable, id=" + id));
        return PatientMapper.toDTO(p);
    }
}