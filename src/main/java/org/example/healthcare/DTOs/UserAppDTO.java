package org.example.healthcare.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.Enums.RoleUser;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAppDTO {
    private Long id;
    private String nom;
    private String email;
    private String password;
    private RoleUser role;
}
