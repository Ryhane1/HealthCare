package Service;

import DTOs.MedecinDTO;
import Mappers.MedecinMapper;
import Model.Medecin;
import Repository.MedecinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;

    public MedecinDTO ajouterMedecin(MedecinDTO medecinDTO){
        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        return medecinMapper.toDTO(medecinRepository.save(medecin));

    }

    public MedecinDTO editMedecin(Long id , MedecinDTO medecinDTO){
        if (medecinRepository.findById(id).isPresent()){
            Medecin medecin = medecinMapper.toEntity(medecinDTO);
            medecin.setId(id);
            return medecinMapper.toDTO(medecinRepository.save(medecin));
        } else {
            return null;
        }

    }

    public void supprimerMedecin(Long id){
        medecinRepository.deleteById(id);
    }

    public List<MedecinDTO> listerMedecin(){
        return medecinMapper.toDTOList(medecinRepository.findAll());
    }



}
