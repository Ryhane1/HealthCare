package org.example.healthcare.Service;

import org.example.healthcare.DTOs.DossierMedicalDTO;
import org.example.healthcare.Mappers.DossierMedicalMapper;
import org.example.healthcare.Model.DossierMedical;
import org.example.healthcare.Repository.DossierMedicalRepository;
import org.example.healthcare.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final PatientRepository patientRepository;


    public DossierMedicalDTO creerDossierMedical(DossierMedicalDTO dossierMedicalDTO){
        DossierMedical dossier = dossierMedicalMapper.toEntity(dossierMedicalDTO);
        dossier.setDateCreation(LocalDate.now());
        dossier.setPatient(patientRepository.findById(dossierMedicalDTO.getPatientId()).orElseThrow());
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.save(dossier));
    }

    public DossierMedicalDTO ajouterDiagnostic (Long id , String diagnostic){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow();
        dossierMedical.setDiagnostic(diagnostic);
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.save(dossierMedical));
    }

    public DossierMedicalDTO ajouterObservation (Long id , String observation){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow();
        dossierMedical.setObservation(observation);
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.save(dossierMedical));
    }

    public DossierMedicalDTO consulterDossierMedical(Long id){
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.findById(id).orElseThrow());
    }


}
