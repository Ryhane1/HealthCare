package org.example.healthcare.Service;

import org.example.healthcare.DTOs.MedecinDTO;
import org.example.healthcare.Mappers.MedecinMapper;
import org.example.healthcare.Model.Medecin;
import org.example.healthcare.Repository.MedecinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedecinService {

    private final MedecinRepository medecinRepository;
    private final MedecinMapper medecinMapper;

    @CacheEvict(value = "medecins", allEntries = true)
    public MedecinDTO ajouterMedecin(MedecinDTO medecinDTO){
        Medecin medecin = medecinMapper.toEntity(medecinDTO);
        return medecinMapper.toDTO(medecinRepository.save(medecin));

    }

    @CacheEvict(value = {"medecins","medecinsBySpecialite"}, allEntries = true)
    public MedecinDTO editMedecin(Long id , MedecinDTO medecinDTO){
        if (medecinRepository.findById(id).isPresent()){
            Medecin medecin = medecinMapper.toEntity(medecinDTO);
            medecin.setId(id);
            return medecinMapper.toDTO(medecinRepository.save(medecin));
        } else {
            return null;
        }
    }

    @CacheEvict(value = "medecins", allEntries = true)
    public void supprimerMedecin(Long id){
        medecinRepository.deleteById(id);
    }


    @Cacheable(value = "medecins",key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort")
    public Page<MedecinDTO> listerMedecin(Pageable pageable){
        Page<Medecin> medecinList = medecinRepository.findAll(pageable);
        return medecinList.map(medecinMapper::toDTO);
    }

    @Cacheable(value = "medecinsBySpecialite", key = "#specialite + '-' + #pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort")
    public Page<MedecinDTO> chercherParSpecialite(String specialite, Pageable pageable) {
        Page<Medecin> medecinList = medecinRepository.findBySpecialite(specialite, pageable);
        return medecinList.map(medecinMapper::toDTO);
    }
}
