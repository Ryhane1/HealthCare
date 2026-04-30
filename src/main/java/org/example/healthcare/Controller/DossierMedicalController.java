package org.example.healthcare.Controller;

import org.example.healthcare.DTOs.DossierMedicalDTO;
import org.example.healthcare.Service.DossierMedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dossierMedical")
public class DossierMedicalController {

    private final DossierMedicalService dossierMedicalService;

    @PostMapping
    public ResponseEntity<DossierMedicalDTO> creerDossierMedical
            (@RequestBody DossierMedicalDTO dossierMedicalDTO){
        DossierMedicalDTO dossierMedicalDTO1 = dossierMedicalService.creerDossierMedical(dossierMedicalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dossierMedicalDTO1);
    }

    @PatchMapping("/{id}/diagnostic")
    public ResponseEntity<DossierMedicalDTO> ajouterDiagnostic
            (@PathVariable Long id,
             @RequestBody String diagnostic){
        DossierMedicalDTO dossier = dossierMedicalService.ajouterDiagnostic(id, diagnostic);
        return ResponseEntity.ok(dossier);
    }

    @PatchMapping("/{id}/Observation")
    public ResponseEntity<DossierMedicalDTO> ajouterObservation
            (@PathVariable Long id,
             @RequestBody String observation){
        DossierMedicalDTO dossier = dossierMedicalService.ajouterObservation(id, observation);
        return ResponseEntity.ok(dossier);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedicalDTO> getDossierMedical(@PathVariable Long id){
        DossierMedicalDTO dossier = dossierMedicalService.consulterDossierMedical(id);
        return ResponseEntity.ok(dossier);
    }







}
