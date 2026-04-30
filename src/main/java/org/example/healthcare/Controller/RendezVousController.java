package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.healthcare.DTOs.RendezVousDTO;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rendezVous")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    @PostMapping
    @Operation(summary = "Créer un rendez-vous")
    public ResponseEntity<RendezVousDTO> creerRendezVous
            (@RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO rendezVous = rendezVousService.creerRendezVous(rendezVousDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVous);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un rendez-vous")
    public ResponseEntity<RendezVousDTO> modifierRendezVous
            (@PathVariable Long id, @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO rendezVousDTO1 = rendezVousService.modifierRendezVous(id, rendezVousDTO);
        return ResponseEntity.ok(rendezVousDTO1);

    }

    @PatchMapping("/{id}/annuler")
    @Operation(summary = "Annuler un rendez-vous")
    public ResponseEntity<RendezVousDTO> annulerrendezVous
            (@PathVariable Long id) {

        RendezVousDTO rendezVousDTO = rendezVousService.annulerRendezVous(id);
        return ResponseEntity.ok(rendezVousDTO);
    }

    @GetMapping
    @Operation(summary = "Lister tous les rendez-vous")
    public ResponseEntity<List<RendezVousDTO>> listerRendezVous (){
        return ResponseEntity.ok(rendezVousService.listerRendezVous());
    }

    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Rechercher les rendez-vous par patient")
    public ResponseEntity<List<RendezVousDTO>> filtererParPatient
            (@PathVariable Long patientId){
        return ResponseEntity.ok(rendezVousService.filtrerParPatient(patientId));
    }

    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "Rechercher les rendez-vous par médecin")
    public ResponseEntity<List<RendezVousDTO>> filtererParMadecin
            (@PathVariable Long medecinId){
        return ResponseEntity.ok(rendezVousService.filtrerParMedecin(medecinId));
    }




}
