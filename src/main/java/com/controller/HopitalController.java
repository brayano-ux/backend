package com.mbemnova.epidemie.controller;

import com.mbemnova.epidemie.dto.request.HopitalRequestDTO;
import com.mbemnova.epidemie.dto.response.HopitalResponseDTO;
import com.mbemnova.epidemie.service.HopitalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hopitaux")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200", "https://frontend-pm86.onrender.com"})
public class HopitalController {

    private final HopitalService hopitalService;

    @PostMapping
    public ResponseEntity<HopitalResponseDTO> creer(@Valid @RequestBody HopitalRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hopitalService.creer(dto));
    }

    @GetMapping
    public ResponseEntity<List<HopitalResponseDTO>> listerTous() {
        return ResponseEntity.ok(hopitalService.listerTous());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HopitalResponseDTO> recupererParId(@PathVariable Long id) {
        return ResponseEntity.ok(hopitalService.recupererParId(id));
    }

    @GetMapping("/disponibles/{patientId}")
    public ResponseEntity<List<HopitalResponseDTO>> hopitauxDisponibles(@PathVariable Long patientId) {
        return ResponseEntity.ok(hopitalService.hopitauxDisponibles(patientId));
    }
}