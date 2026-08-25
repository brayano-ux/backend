package com.mbemnova.epidemie.controller;

import com.mbemnova.epidemie.dto.request.RendezVousRequestDTO;
import com.mbemnova.epidemie.dto.response.RendezVousResponseDTO;
import com.mbemnova.epidemie.dto.response.StatistiquesDTO;
import com.mbemnova.epidemie.service.RendezVousService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/rendezvous")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @PostMapping
    public ResponseEntity<RendezVousResponseDTO> creer(@Valid @RequestBody RendezVousRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVousService.creer(dto));
    }

    @GetMapping
    public ResponseEntity<List<RendezVousResponseDTO>> listerTous() {
        return ResponseEntity.ok(rendezVousService.listerTous());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendezVousResponseDTO> modifier(
            @PathVariable Long id, @Valid @RequestBody RendezVousRequestDTO dto
    ) {
        return ResponseEntity.ok(rendezVousService.modifier(id, dto));
    }

    @PutMapping("/{id}/annuler")
    public ResponseEntity<RendezVousResponseDTO> annuler(@PathVariable Long id) {
        return ResponseEntity.ok(rendezVousService.annuler(id));
    }

    @GetMapping("/statistiques")
    public ResponseEntity<StatistiquesDTO> getStatistiques() {
        return ResponseEntity.ok(rendezVousService.getStatistiques());
    }
}