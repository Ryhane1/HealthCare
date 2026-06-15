package org.example.healthcare.DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedicalDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String diagnostic;
    private String observation;
    private LocalDate dateCreation;
    @NotNull
    private Long patientId;

}
