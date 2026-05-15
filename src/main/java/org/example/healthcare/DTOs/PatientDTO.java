package org.example.healthcare.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    private Long id ;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    @Email
    private String email;
    private String telephone;
    private LocalDate dateNaissance;

}
