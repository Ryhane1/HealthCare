package org.example.healthcare.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.healthcare.DTOs.RendezVousDTO;
import org.example.healthcare.Enums.StatutRendezVous;
import org.example.healthcare.Mappers.RendezVousMapper;
import org.example.healthcare.Model.Patient;
import org.example.healthcare.Model.RendezVous;
import org.example.healthcare.Repository.MedecinRepository;
import org.example.healthcare.Repository.PatientRepository;
import org.example.healthcare.Repository.RendezVousRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final RendezVousMapper rendezVousMapper;
    private final PatientRepository  patientRepository;
    private final MedecinRepository medecinRepository;

    @CacheEvict(value = "rendezVous" , allEntries = true)
    public RendezVousDTO creerRendezVous(RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = rendezVousMapper.toEntity(rendezVousDTO);
        rendezVous.setPatient(patientRepository.findById(rendezVousDTO.getPatientId()).orElseThrow());
        rendezVous.setMedecin(medecinRepository.findById(rendezVousDTO.getMedecinId()).orElseThrow());
        rendezVous.setStatut(StatutRendezVous.EN_ATTENTE);
        return rendezVousMapper.toDTO(rendezVousRepository.save(rendezVous));
    }

    @CacheEvict(value = "rendezVous", allEntries = true)
    public RendezVousDTO modifierRendezVous( Long id, RendezVousDTO rendezVousDTO) {
        RendezVous rendezVous = rendezVousRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rendez-vous introuvable"));
        rendezVous.setDateRendezVous(rendezVousDTO.getDateRendezVous());
        rendezVous.setStatut(rendezVousDTO.getStatut());
        rendezVous.setMedecin(medecinRepository.findById(rendezVousDTO.getMedecinId()).orElseThrow());
        rendezVous.setPatient(patientRepository.findById(rendezVousDTO.getPatientId()).orElseThrow());
        return rendezVousMapper.toDTO(rendezVousRepository.save(rendezVous));

    }

    @CacheEvict(value = "rendezVous", allEntries = true)
    public void supprimerRendezVous(Long id) {
        if (rendezVousRepository.findById(id).isPresent()) {
            rendezVousRepository.deleteById(id);
        }
    }

    @CacheEvict(value = "rendezVous", allEntries = true)
    public RendezVousDTO annulerRendezVous(Long id) {
            RendezVous rendezVous=rendezVousRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Rendez-vous introuvable")) ;
            rendezVous.setStatut(StatutRendezVous.ANNULE);
            return rendezVousMapper.toDTO(rendezVousRepository.save(rendezVous));

    }

    @Cacheable(value = "rendezVous", key = "#pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<RendezVousDTO> listerRendezVous(Pageable pageable){
         Page<RendezVous> rendezVousList = rendezVousRepository.findAll(pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
    }

    @Cacheable(value = "rendezVousByPatient", key = "#id + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<RendezVousDTO> filtrerParPatient(Long id , Pageable pageable){
        Page<RendezVous> rendezVousList =
                rendezVousRepository.findByPatient_Id(id, pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
    }

    @Cacheable(value = "rendezVousByMedecin", key = "#id + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<RendezVousDTO> filtrerParMedecin(Long id, Pageable pageable){
        Page<RendezVous> rendezVousList =
                rendezVousRepository.findByMedecinId(id , pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
    }

    @Cacheable(value = "rendezVousByDate", key = "#date + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<RendezVousDTO> chercherParDate(LocalDate date, Pageable pageable) {
        Page<RendezVous> rendezVousList = rendezVousRepository.findByDateRendezVous(date, pageable);
        return rendezVousList.map(rendezVousMapper::toDTO);
    }

    public byte[] genererListeRendezVousPdf(Long patientId) throws Exception {

        List<RendezVous> list = rendezVousRepository.findAllByPatientId(patientId);
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,out);
        document.open();
        document.add(new Paragraph());
        document.add(new Paragraph("Liste des rendez-vous"));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(
                "Patient : " +
                        patient.getNom() + " " + patient.getPrenom()));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(3);

        table.addCell("Rendez-Vous ID");
        table.addCell("Date");
        table.addCell("Patient");
        table.addCell("Medecin");
        table.addCell("Statut");

        for (RendezVous rdv : list) {

            table.addCell(rdv.getId().toString());
            table.addCell(String.valueOf(rdv.getDateRendezVous()));
            table.addCell(rdv.getPatient().toString());
            table.addCell(rdv.getMedecin().toString());
            table.addCell(rdv.getStatut().toString());
        }

        document.add(table);
        document.close();

        return out.toByteArray();

    }
}
