package org.example.healthcare.Mappers;

import org.example.healthcare.DTOs.RendezVousDTO;
import org.example.healthcare.Model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")



public interface RendezVousMapper {


    @Mapping(source = "patient.id" , target = "patientId")
    @Mapping(source = "medecin.id" , target = "medecinId")
    RendezVousDTO toDTO(RendezVous rendezVous);

    @Mapping(target = "patient" , ignore = true)
    @Mapping(target = "medecin" , ignore = true)
    RendezVous toEntity(RendezVousDTO rendezVousDTO);
//    Page<RendezVousDTO> toDTOPage(Page<RendezVous> rendezVous);

}
