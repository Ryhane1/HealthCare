package org.example.healthcare;

import org.example.healthcare.DTOs.PatientDTO;
import org.example.healthcare.Mappers.PatientMapper;
import org.example.healthcare.Model.Patient;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientTestService {


    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private PatientDTO patientDTO;

    @BeforeEach
    void setUp() {
        patient = new Patient();
        patient.setId(1L);

        patientDTO = new PatientDTO();
    }

    @Test
    void testAjouterPatient() {
        when(patientMapper.toEntity(patientDTO)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toDTO(patient)).thenReturn(patientDTO);

        PatientDTO result = patientService.AjouterPatient(patientDTO);

        assertNotNull(result);
        verify(patientRepository, times(1)).save(patient);
    }

    @Test
    void testEditPatient_WhenExists() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientMapper.toEntity(patientDTO)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toDTO(patient)).thenReturn(patientDTO);

        PatientDTO result = patientService.editPatient(1L, patientDTO);

        assertNotNull(result);
        verify(patientRepository).save(patient);
    }

    @Test
    void testEditPatient_WhenNotExists() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        PatientDTO result = patientService.editPatient(1L, patientDTO);

        assertNull(result);
        verify(patientRepository, never()).save(any());
    }

    @Test
    void testSupprimerPatient() {
        doNothing().when(patientRepository).deleteById(1L);

        patientService.SupprimerPatient(1L);

        verify(patientRepository, times(1)).deleteById(1L);
    }

    @Test
    void testListerPatients() {
        List<Patient> patients = Arrays.asList(patient);

        when(patientRepository.findAll()).thenReturn(patients);
        when(patientMapper.toDTOList(patients)).thenReturn(List.of(patientDTO));

        List<PatientDTO> result = patientService.listerPatients();

        assertEquals(1, result.size());
        verify(patientRepository).findAll();
    }

    @Test
    void testConsulterPatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientMapper.toDTO(patient)).thenReturn(patientDTO);

        PatientDTO result = patientService.consulterPatient(1L);

        assertNotNull(result);
        verify(patientRepository).findById(1L);
    }


}
