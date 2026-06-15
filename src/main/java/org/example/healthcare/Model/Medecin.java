package org.example.healthcare.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("MEDECIN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medecin extends UserApp {

    private String specialite;
}
