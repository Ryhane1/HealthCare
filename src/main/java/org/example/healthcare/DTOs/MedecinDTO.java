package org.example.healthcare.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDTO {

    private Long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String specialite;
    @NotBlank
    @Email
    private String email;
    private String telephone;
}
