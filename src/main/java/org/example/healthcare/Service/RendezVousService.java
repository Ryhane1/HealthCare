package org.example.healthcare.Service;

import org.example.healthcare.DTOs.RendezVousDTO;
import org.example.healthcare.Enums.StatutRendezVous;
import org.example.healthcare.Mappers.RendezVousMapper;
import org.example.healthcare.Model.RendezVous;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Repository.RendezVousRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final RendezVousMapper rendezVousMapper;
    private final PatientRepository  patientRepository;
    private final MedecinRepository medecinRepository;

    public RendezVousDTO creerRendezVous(RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = rendezVousMapper.toEntity(rendezVousDTO);
        rendezVous.setPatient(patientRepository.findById(rendezVousDTO.getPatientId()).orElseThrow());
        rendezVous.setMedecin(medecinRepository.findById(rendezVousDTO.getMedecinId()).orElseThrow());
        rendezVous.setStatut(StatutRendezVous.EN_ATTENTE);
        return rendezVousMapper.toDTO(rendezVousRepository.save(rendezVous));
    }

    public RendezVousDTO modifierRendezVous( Long id, RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rendez-vous introuvable"));
        rendezVous.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        rendezVous.setStatut(rendezVousDTO.getStatut());
        rendezVous.setMedecin(medecinRepository.findById(rendezVousDTO.getMedecinId()).orElseThrow());
        rendezVous.setPatient(patientRepository.findById(rendezVousDTO.getPatientId()).orElseThrow());
        return rendezVousMapper.toDTO(rendezVousRepository.save(rendezVous));

    }

    public void supprimerRendezVous(Long id) {
        if (rendezVousRepository.findById(id).isPresent()) {
            rendezVousRepository.deleteById(id);
        }
    }

    public RendezVousDTO annulerRendezVous(Long id) {
            RendezVous rendezVous=rendezVousRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Rendez-vous introuvable")) ;
            rendezVous.setStatut(StatutRendezVous.ANNULE);
            return rendezVousMapper.toDTO(rendezVousRepository.save(rendezVous));

    }

    public Page<RendezVousDTO> listerRendezVous(Pageable pageable){
         Page<RendezVous> rendezVousList = rendezVousRepository.findAll(pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
    }

    public Page<RendezVousDTO> filtrerParPatient(Long id , Pageable pageable){
        Page<RendezVous> rendezVousList =
                rendezVousRepository.findByPatient_Id(id, pageable);

        return rendezVousList.map(rendezVousMapper::toDTO);
    }

    public Page<RendezVousDTO> filtrerParMedecin(Long id, Pageable pageable){
        Page<RendezVous> rendezVousList =
                rendezVousRepository.findByMedecinId(id , pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
    }
    public Page<RendezVousDTO> chercherParDate(LocalDate date, Pageable pageable) {
        Page<RendezVous> rendezVousList = rendezVousRepository.findByDateRendezVous(date, pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
    }

}
