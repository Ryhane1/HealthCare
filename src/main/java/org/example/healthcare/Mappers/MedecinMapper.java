package org.example.healthcare.Mappers;

import org.example.healthcare.DTOs.MedecinDTO;
import org.example.healthcare.Model.Medecin;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface MedecinMapper {
    MedecinDTO toDTO(Medecin medecin);
    Medecin toEntity(MedecinDTO medecinDTO);
//    Page<MedecinDTO> toDTOPage(Page<Medecin> medecins);
}
