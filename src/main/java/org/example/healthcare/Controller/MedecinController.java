package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.healthcare.DTOs.MedecinDTO;
import org.example.healthcare.Service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medecin")
public class MedecinController  {

    private final MedecinService medecinService;

    @PostMapping
    @Operation(summary = "Ajouter un médecin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedecinDTO> ajouterMedecin(@RequestBody MedecinDTO medecinDTO){
        MedecinDTO medecinDTO1 = medecinService.ajouterMedecin(medecinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medecinDTO1);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Modifier un médecin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedecinDTO> editMedecin(@PathVariable Long id,
                                                  @RequestBody MedecinDTO medecinDTO){
        MedecinDTO medecinDTO1 = medecinService.editMedecin(id, medecinDTO);
        return ResponseEntity.ok().body(medecinService.editMedecin(id,medecinDTO1));


    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un médecin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerMedecin(@PathVariable Long id){
        medecinService.supprimerMedecin(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Lister tous les médecins")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<MedecinDTO>> listerMedecin (Pageable pageable){
        return ResponseEntity.ok(medecinService.listerMedecin(pageable));
    }

    @GetMapping("/triParSpecialite")
    @Operation(summary = "Lister tous les médecins Tries par spécialité")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<MedecinDTO>> listerMedecinParSpecialite (Pageable pageable){
        return ResponseEntity.ok(medecinService.listerMedecin(pageable));
    }

    @GetMapping("/chercherParSpecialite")
    @Operation(summary = "Lister tous les médecins Tries par spécialité")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<MedecinDTO>> charcherMedecinParSpecialite
            (@RequestParam String specialite, Pageable pageable){
        return ResponseEntity.ok(medecinService.chercherParSpecialite(specialite,pageable));
    }


}
