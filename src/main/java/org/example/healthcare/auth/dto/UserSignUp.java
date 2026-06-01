package org.example.healthcare.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.healthcare.Enums.RoleUser;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUp {
    @NotBlank
    private String nom;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 4)
    private String password;
    private RoleUser role;
    private String telephone;

    private String specialite;
    private String prenom;
    private LocalDate dateNaissance;



}
