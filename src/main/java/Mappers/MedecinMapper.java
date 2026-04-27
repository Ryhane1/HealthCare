package Mappers;

import DTOs.MedecinDTO;
import Model.Medecin;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    MedecinDTO toDTO(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);
    List<MedecinDTO> toDTOList(List<Medecin> medecins);
}
