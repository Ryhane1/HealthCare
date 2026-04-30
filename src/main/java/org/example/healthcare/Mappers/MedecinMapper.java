package org.example.healthcare.Mappers;

import org.example.healthcare.DTOs.MedecinDTO;
import org.example.healthcare.Model.Medecin;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    MedecinDTO toDTO(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);
    List<MedecinDTO> toDTOList(List<Medecin> medecins);
}
