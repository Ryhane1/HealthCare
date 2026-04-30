package org.example.healthcare.Mappers;

import org.example.healthcare.DTOs.RendezVousDTO;
import org.example.healthcare.Model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")



public interface RendezVousMapper {


    @Mapping(source = "patient.id" , target = "patientId")
    @Mapping(source = "medecin.id" , target = "medecinId")
    RendezVousDTO toDTO(RendezVous rendezVous);

    @Mapping(target = "patient" , ignore = true)
    @Mapping(target = "medecin" , ignore = true)
    RendezVous toEntity(RendezVousDTO rendezVousDTO);
    List<RendezVousDTO> toDTOlist(List<RendezVous> rendezVous);

}
