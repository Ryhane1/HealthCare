package org.example.healthcare.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.healthcare.Enums.RoleUser;

import java.util.ArrayList;

@Entity
@Table(name = "userapp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser role;

}
