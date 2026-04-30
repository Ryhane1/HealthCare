package org.example.healthcare.Mappers;

import org.example.healthcare.DTOs.PatientDTO;
import org.example.healthcare.Model.Patient;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDTO(Patient patient);
    Patient toEntity(PatientDTO patientDTO);
    List<PatientDTO> toDTOList(List<Patient> patients);

}
