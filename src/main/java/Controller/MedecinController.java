package Controller;

import DTOs.MedecinDTO;
import Model.Medecin;
import Service.MedecinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medecin")
public class MedecinController {

    private final MedecinService medecinService;

    @PostMapping
    public ResponseEntity<MedecinDTO> ajouterMedecin(@RequestBody MedecinDTO medecinDTO){
        MedecinDTO medecinDTO1 = medecinService.ajouterMedecin(medecinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(medecinDTO1);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MedecinDTO> editMedecin(@PathVariable Long id,
                                                  @RequestBody MedecinDTO medecinDTO){
        MedecinDTO medecinDTO1 = medecinService.editMedecin(id, medecinDTO);
        return ResponseEntity.ok().body(medecinService.editMedecin(id,medecinDTO1));


    }

    @GetMapping
    public ResponseEntity<MedecinDTO>



}
