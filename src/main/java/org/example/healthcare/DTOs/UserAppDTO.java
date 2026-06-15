package org.example.healthcare.DTOs;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.healthcare.Enums.RoleUser;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAppDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nom;
    private String email;
    private String password;
    private RoleUser role;
}
