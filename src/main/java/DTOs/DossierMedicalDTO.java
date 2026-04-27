package DTOs;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedicalDTO {
    private Long id;
    private String diagnostic;
    private String observation;
    private LocalDate dateCreation;
    @NotNull
    private Long patientId;

}
