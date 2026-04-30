package org.example.healthcare.Mappers;

import org.example.healthcare.DTOs.DossierMedicalDTO;
import org.example.healthcare.Model.DossierMedical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMedicalMapper {

    @Mapping(source = "patient.id" , target = "patientId")
    DossierMedicalDTO toDTo(DossierMedical dossierMedical);

    @Mapping(target = "patient" , ignore = true)
    DossierMedical toEntity(DossierMedicalDTO dossierMedicalDTO);
    List<DossierMedicalDTO> toDtoList(List<DossierMedical> dossierMedicals);

}
