package com.mbemnova.epidemie.controller;

import com.mbemnova.epidemie.dto.request.PatientRequestDTO;
import com.mbemnova.epidemie.dto.response.PatientResponseDTO;
import com.mbemnova.epidemie.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> creer(@Valid @RequestBody PatientRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.creer(dto));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> listerTous() {
        return ResponseEntity.ok(patientService.listerTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> recupererParId(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.recupererParId(id));
    }
}