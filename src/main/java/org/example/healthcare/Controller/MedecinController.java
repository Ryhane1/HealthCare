package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.healthcare.DTOs.MedecinDTO;
import org.example.healthcare.Service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medecin")
public class MedecinController {

    private final MedecinService medecinService;

    @PostMapping
    @Operation(summary = "Ajouter un médecin")
    public ResponseEntity<MedecinDTO> ajouterMedecin(@RequestBody MedecinDTO medecinDTO){
        MedecinDTO medecinDTO1 = medecinService.ajouterMedecin(medecinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medecinDTO1);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Modifier un médecin")
    public ResponseEntity<MedecinDTO> editMedecin(@PathVariable Long id,
                                                  @RequestBody MedecinDTO medecinDTO){
        MedecinDTO medecinDTO1 = medecinService.editMedecin(id, medecinDTO);
        return ResponseEntity.ok().body(medecinService.editMedecin(id,medecinDTO1));


    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un médecin")
    public ResponseEntity<Void> supprimerMedecin(@PathVariable Long id){
        medecinService.supprimerMedecin(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Lister tous les médecins")
    public ResponseEntity<List<MedecinDTO>> listerMedecin (){

        return ResponseEntity.ok(medecinService.listerMedecin());
    }




}
