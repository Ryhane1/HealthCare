package org.example.healthcare.Service;

import org.example.healthcare.DTOs.PatientDTO;
import org.example.healthcare.Mappers.PatientMapper;
import org.example.healthcare.Model.Patient;
import org.example.healthcare.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<PatientDTO> listerPatients(){
        return patientMapper.toDTOList(patientRepository.findAll());
    }

    public PatientDTO consulterPatient (Long id){
        return patientMapper.toDTO(patientRepository.findById(id).get());
    }





}
