package Mappers;

import DTOs.RendezVousDTO;
import Model.RendezVous;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.accept.MediaTypeFileExtensionResolver;

@Mapper(componentModel = "spring")
public interface RendezVousMapper {


    @Mapping(source = "patient.id" , target = "patientId")
    @Mapping(source = "medecin.id" , target = "medecinId")
    RendezVousDTO toDTO(RendezVous rendezVous);

    @Mapping(target = "patient" , ignore = true)
    @Mapping(target = "medecin" , ignore = true)
    RendezVous toEntity(RendezVousDTO rendezVousDTO);

}
