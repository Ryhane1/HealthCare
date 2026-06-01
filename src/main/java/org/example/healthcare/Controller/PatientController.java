package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.healthcare.DTOs.PatientDTO;
import org.example.healthcare.Service.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;


    @PostMapping
    @Operation(summary = "Ajouter un patient")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTO> ajouterPatient
            (@RequestBody PatientDTO patientDTO ){
        PatientDTO patientDTO1 = patientService.AjouterPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDTO1);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un patient")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTO> modifierPatient (@RequestBody PatientDTO patientDTO ,
    @PathVariable Long id){
        PatientDTO patientDTO1 = patientService.editPatient(id, patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDTO1);
    }

    @DeleteMapping
    @Operation(summary = "Supprimer un patient")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerPatient (@RequestParam Long id){
        patientService.SupprimerPatient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter les détails d’un patient")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTO> consulterPatient (@PathVariable Long id){
        return ResponseEntity.ok().body(patientService.consulterPatient(id));
    }

    @GetMapping("/monCompte")
    @Operation(summary = "Patient Consulter les détails de son compte")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<PatientDTO> consulterCompte (Authentication authentication){
        String username = authentication.getName();
        return ResponseEntity.ok().body(patientService.consulterPatientParNom(username));
    }

    @GetMapping
    @Operation(summary = "Lister tous les patients")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PatientDTO>> lisitePatient (Pageable pageable){
        Page<PatientDTO> dtoList = patientService.listerPatients(pageable);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/triParNom")
    @Operation(summary = "Lister tous les patients triés Par nom")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PatientDTO>> lisiterParNom (Pageable pageable){
        Page<PatientDTO> dtoList = patientService.listerPatients(pageable);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/chercherParNom")
    @Operation(summary = "Chercher les patients Par nom")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<PatientDTO>> chercherParNom (@RequestParam String nom,
                                                          Pageable pageable){
        Page<PatientDTO> dtoList = patientService.chercherPatients(nom, pageable);
        return ResponseEntity.ok().body(dtoList);
    }


}
