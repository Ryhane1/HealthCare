package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTOs.UserAppDTO;
import org.example.healthcare.Service.UserAppService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UseAppController {

    private final UserAppService userAppService;

    @PostMapping
    @Operation(summary = "Ajouter un User")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserAppDTO> ajouterPatient
            (@RequestBody UserAppDTO userAppDTO ){
        UserAppDTO userAppDTO1 = userAppService.AjouterUser(userAppDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAppDTO1);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un User")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserAppDTO> modifierUser (@RequestBody UserAppDTO userAppDTO ,
                                                       @PathVariable Long id){
        UserAppDTO userAppDTO1 = userAppService.editUser(id, userAppDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAppDTO1);
    }

    @DeleteMapping
    @Operation(summary = "Supprimer un user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerUser (@RequestParam Long id){
        userAppService.SupprimerUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(summary = "Lister tous les users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<UserAppDTO>> lisiterUsers (Pageable pageable){
        Page<UserAppDTO> dtoList = userAppService.listerUsers(pageable);
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consulter les détails d’un patient")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserAppDTO> consulterUser (@PathVariable Long id){

        return ResponseEntity.ok().body(userAppService.consulterUser(id));
    }

    @GetMapping("/rapport")
    @Operation(summary = "Telecharger un rapport global ")
    @PreAuthorize("hasRole('ADMIN')")
    public byte[] telechargerRapportGlobal() throws Exception{
        byte[] pdfBytes = userAppService.genererRapportGlobal();
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=Rapport_Global"  + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes).getBody();
    }



}
