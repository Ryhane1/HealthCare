package org.example.healthcare.Controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.healthcare.DTOs.RendezVousDTO;
import org.example.healthcare.Model.Patient;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Service.PatientService;
import org.example.healthcare.Service.RendezVousService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rendezVous")
public class RendezVousController {

    private final RendezVousService rendezVousService;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;

    @PostMapping
    @Operation(summary = "Créer un rendez-vous")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RendezVousDTO> creerRendezVous
            (@RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO rendezVous = rendezVousService.creerRendezVous(rendezVousDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(rendezVous);

    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un rendez-vous")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RendezVousDTO> modifierRendezVous
            (@PathVariable Long id, @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO rendezVousDTO1 = rendezVousService.modifierRendezVous(id, rendezVousDTO);
        return ResponseEntity.ok(rendezVousDTO1);

    }

    @PatchMapping("/{id}/annuler")
    @Operation(summary = "Annuler un rendez-vous")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RendezVousDTO> annulerrendezVous
            (@PathVariable Long id) {
        RendezVousDTO rendezVousDTO = rendezVousService.annulerRendezVous(id);
        return ResponseEntity.ok(rendezVousDTO);
    }


    @GetMapping("/patient/{patientId}")
    @Operation(summary = "Rechercher les rendez-vous par patient")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<RendezVousDTO>> filtererParPatient
            (@PathVariable Long patientId , Pageable pageable){
        return ResponseEntity.ok(rendezVousService.filtrerParPatient(patientId, pageable));
    }

    @GetMapping("/patient/{patientId}/telecharger")
    @Operation(summary = "Telecharger la liste des Rendez-Vous d'un patient")
    public byte[] telechargerRVPatient(@PathVariable Long patientId) throws Exception{
        byte[] pdf =
                rendezVousService.genererListeRendezVousPdf(patientId);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=rendez-vous-patient-"
                                + patientId + ".pdf")
                .body(pdf).getBody();
    }

    @GetMapping("/patient")
    @Operation(summary = "Patient Consulter les rendez-vous de son compte")
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Page<RendezVousDTO>> consulterRendezVousParPatient
            (Authentication authentication, Pageable pageable){
            Long patientId = patientRepository.findByNom(authentication.getName()).getId();
        return ResponseEntity.ok(rendezVousService.filtrerParPatient(patientId, pageable));
    }

    @GetMapping("/medecin")
    @Operation(summary = "Medecin Consulter les rendez-vous de son compte")
    @PreAuthorize("hasRole('MEDECIN')")
    public ResponseEntity<Page<RendezVousDTO>> consulterRendezVousParMedecin
            (Authentication authentication, Pageable pageable){
        Long medecinId = medecinRepository.findByNom(authentication.getName()).getId();
        return ResponseEntity.ok(rendezVousService.filtrerParMedecin(medecinId, pageable));
    }


    @GetMapping("/medecin/{medecinId}")
    @Operation(summary = "Rechercher les rendez-vous par médecin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<RendezVousDTO>> filtererParMadecin
            (@PathVariable Long medecinId, Pageable pageable){
        return ResponseEntity.ok(rendezVousService.filtrerParMedecin(medecinId, pageable));
    }

    @GetMapping
    @Operation(summary = "Lister tous les rendez-vous")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<RendezVousDTO>> listerRendezVous (Pageable pageable){
        return ResponseEntity.ok(rendezVousService.listerRendezVous(pageable));
    }

    @GetMapping("/triParDate")
    @Operation(summary = "Lister tous les rendez-vous Tries par Date")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<RendezVousDTO>> listerRendezVousParDate (Pageable pageable){
        return ResponseEntity.ok(rendezVousService.listerRendezVous(pageable));
    }

    @GetMapping("/chercherParDate")
    @Operation(summary = "Lister tous les rendez-vous Tries par Date")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<RendezVousDTO>> charcherParDate
            (@RequestParam LocalDate date, Pageable pageable){
        return ResponseEntity.ok(rendezVousService.chercherParDate(date, pageable));
    }





}
