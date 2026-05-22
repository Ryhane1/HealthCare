package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.healthcare.DTOs.DossierMedicalDTO;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Service.DossierMedicalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dossierMedical")
public class DossierMedicalController {

    private final DossierMedicalService dossierMedicalService;
    private final PatientRepository patientRepository;

    @PostMapping
    @Operation(summary = "Créer un dossier médical")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierMedicalDTO> creerDossierMedical
            (@RequestBody DossierMedicalDTO dossierMedicalDTO){
        DossierMedicalDTO dossierMedicalDTO1 = dossierMedicalService.creerDossierMedical(dossierMedicalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(dossierMedicalDTO1);
    }

    @PatchMapping("/{id}/diagnostic")
    @Operation(summary = "Ajouter un diagnostic au dossier médical")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    public ResponseEntity<DossierMedicalDTO> ajouterDiagnostic
            (@PathVariable Long id,
             @RequestBody String diagnostic){
        DossierMedicalDTO dossier = dossierMedicalService.ajouterDiagnostic(id, diagnostic);
        return ResponseEntity.ok(dossier);
    }

    @PatchMapping("/{id}/Observation")
    @Operation(summary = "Ajouter des observations au dossier médical")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    public ResponseEntity<DossierMedicalDTO> ajouterObservation
            (@PathVariable Long id,
             @RequestBody String observation){
        DossierMedicalDTO dossier = dossierMedicalService.ajouterObservation(id, observation);
        return ResponseEntity.ok(dossier);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter un dossier médical")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierMedicalDTO> getDossierMedical
            (@PathVariable Long id ){
        DossierMedicalDTO dossier = dossierMedicalService.consulterDossierMedical(id);
        return ResponseEntity.ok(dossier);
    }

    @GetMapping("/patient")
    @Operation(summary = "Patient Consulter les détails de son dossier médical")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<DossierMedicalDTO> getDossierMedicalPatient
            (Authentication authentication){
        Long patientId = patientRepository.findByNom(authentication.getName()).getId();
        DossierMedicalDTO dossier = dossierMedicalService.consulterDossierMedical(patientId);
        return ResponseEntity.ok(dossier);
    }

    @GetMapping
    @Operation(summary = "Consulter les dossiers médical")
    @PreAuthorize("hasAnyRole('ADMIN','MEDECIN')")
    public ResponseEntity<Page<DossierMedicalDTO>> getAllDossierMedical
            (Pageable pageable){
        Page<DossierMedicalDTO> dossiers = dossierMedicalService.consulterAllDossierMedical(pageable);
        return ResponseEntity.ok(dossiers);
    }











}
