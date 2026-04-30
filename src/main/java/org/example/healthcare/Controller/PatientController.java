package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.healthcare.DTOs.PatientDTO;
import org.example.healthcare.Service.PatientService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;


    @PostMapping
    @Operation(summary = "Ajouter un patient")
    public ResponseEntity<PatientDTO> ajouterPatient
            (@RequestBody PatientDTO patientDTO ){
        PatientDTO patientDTO1 = patientService.AjouterPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDTO1);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un patient")
    public ResponseEntity<PatientDTO> modifierPatient (@RequestBody PatientDTO patientDTO ,
    @PathVariable Long id){
        PatientDTO patientDTO1 = patientService.editPatient(id, patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDTO1);
    }

    @DeleteMapping
    @Operation(summary = "Supprimer un patient")
    public ResponseEntity<Void> supprimerPatient (@RequestParam Long id){
        patientService.SupprimerPatient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Lister tous les patients")
    public ResponseEntity<List<PatientDTO>> lisitePatient (){
        List<PatientDTO> dtoList = patientService.listerPatients();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter les détails d’un patient")
    public ResponseEntity<PatientDTO> consulterPatient (@RequestParam Long id){

        return ResponseEntity.ok().body(patientService.consulterPatient(id));
    }




}
