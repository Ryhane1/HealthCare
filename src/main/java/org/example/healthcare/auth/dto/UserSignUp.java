package org.example.healthcare.auth.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


}
