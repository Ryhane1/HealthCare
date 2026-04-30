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
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<RendezVousDTO>  listerRendezVous(){
        return rendezVousMapper.toDTOlist(rendezVousRepository.findAll());
    }

    public List<RendezVousDTO> filtrerParPatient(Long id){
        List<RendezVous> rendezVousList =
                rendezVousRepository.findByPatient_Id(id);

        return rendezVousMapper.toDTOlist(rendezVousList);
    }

    public List<RendezVousDTO> filtrerParMedecin(Long id){
        List<RendezVous> rendezVousList =
                rendezVousRepository.findByMedecin_Id(id);
        return rendezVousMapper.toDTOlist(rendezVousList);
    }



}
