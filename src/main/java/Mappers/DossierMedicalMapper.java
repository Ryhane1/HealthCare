package Mappers;

import DTOs.DossierMedicalDTO;
import Model.DossierMedical;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {
    DossierMedicalDTO toDTo(DossierMedical dossierMedical);
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);
    List<DossierMedicalDTO> toDtoList(List<DossierMedical> dossierMedicals);

}
