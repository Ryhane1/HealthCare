package org.example.healthcare.Service;

import org.example.healthcare.DTOs.PatientDTO;
import org.example.healthcare.Mappers.PatientMapper;
import org.example.healthcare.Model.Patient;
import org.example.healthcare.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientDTO AjouterPatient (PatientDTO patientDTO) {
        Patient patient = patientMapper.toEntity(patientDTO);
        return patientMapper.toDTO(patientRepository.save(patient));
    }

    public PatientDTO editPatient (Long id , PatientDTO patientDTO){
        if(patientRepository.findById(id).isPresent()){
            Patient patient = patientMapper.toEntity(patientDTO);
            patient.setId(id);
            return patientMapper.toDTO(patientRepository.save(patient));
        }else {
            return null;
        }
    }

    public void SupprimerPatient( Long id){
        patientRepository.deleteById(id);
    }

    public Page<PatientDTO> listerPatients(Pageable pageable){
        Page<Patient> patientList = patientRepository.findAll(pageable);
        return patientList.map(patientMapper::toDTO);
    }

    public Page<PatientDTO> chercherPatients(String mot, Pageable pageable){
        Page<Patient> patientList = patientRepository.findByNomContains(mot, pageable);
        return patientList.map(patientMapper::toDTO);
    }

    public PatientDTO consulterPatient (Long id){
        return patientMapper.toDTO(patientRepository.findById(id).get());
    }

//    public PatientDTO touver (String mot){
//        return patientRepository.trouverAvecdiagnostic(mot).stream().
//    }





}
