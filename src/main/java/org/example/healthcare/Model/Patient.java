package org.example.healthcare.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("PATIENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends UserApp {
    private String prenom;
    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "patient")
    private List<RendezVous> rendezVous;

    @OneToOne(mappedBy = "patient")
    private DossierMedical dossierMedical;

}
