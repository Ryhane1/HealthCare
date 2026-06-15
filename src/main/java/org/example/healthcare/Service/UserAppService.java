package org.example.healthcare.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.example.healthcare.DTOs.UserAppDTO;
import org.example.healthcare.Mappers.UserAppMapper;
import org.example.healthcare.Model.Patient;
import org.example.healthcare.Model.RendezVous;
import org.example.healthcare.Model.UserApp;
import org.example.healthcare.Repository.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserAppService {

    private final UserAppRepository userAppRepository;
    private final UserAppMapper userAppMapper;
    private final PatientRepository patientRepository;
    private final MedecinRepository medecinRepository;
    private final RendezVousRepository rendezVousRepository;
    private final DossierMedicalRepository dossierMedicalRepository;



    @CacheEvict(value = "users", allEntries = true)
    public UserAppDTO AjouterUser (UserAppDTO userAppDTO) {
        UserApp userApp =
                userAppMapper.toEntity(userAppDTO);
        return userAppMapper.toDTO(userAppRepository.save(userApp));
    }

    @CacheEvict(value = "users", allEntries = true)
    public UserAppDTO editUser (Long id , UserAppDTO userAppDTO){
        if(userAppRepository.findById(id).isPresent()){
            UserApp userApp = userAppMapper.toEntity(userAppDTO);
            userApp.setId(id);
            return userAppMapper.toDTO(userAppRepository.save(userApp));
        }else {
            return null;
        }
    }

    @CacheEvict(value = "users", allEntries = true)
    public void SupprimerUser( Long id){
        userAppRepository.deleteById(id);
    }

    @Cacheable(value = "users", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort")
    public Page<UserAppDTO> listerUsers(Pageable pageable){
        Page<UserApp> userList = userAppRepository.findAll(pageable);
        return userList.map(userAppMapper::toDTO);
    }

    @Cacheable(value = "user", key = "#id")
    public UserAppDTO consulterUser (Long id){
        return userAppMapper.toDTO(userAppRepository.findById(id).get());
    }

    public byte[] genererRapportGlobal() throws DocumentException {
        long totalPatients = patientRepository.count();
        long totalMedecins = medecinRepository.count();
        long totalRendezVous = rendezVousRepository.count();
        long totalDossiers = dossierMedicalRepository.count();
//        List<RendezVous> list = rendezVousRepository.findAllByPatientId(patientId);
//        Patient patient = patientRepository.findById(patientId).orElseThrow();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,out);
        document.open();
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);

        Paragraph title = new Paragraph("Rapport Medical HealthCare+", titleFont);
        title.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(title);
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Date : " + java.time.LocalDate.now()));
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Statistiques generales :"));

        document.add(new Paragraph("Nombre total de patients : " + totalPatients));
        document.add(new Paragraph("Nombre total de medecins : " + totalMedecins));
        document.add(new Paragraph("Nombre total de rendez-vous : " + totalRendezVous));
        document.add(new Paragraph("Nombre total de dossiers medicaux : " + totalDossiers));

        document.add(new Paragraph(" "));
        document.add(new Paragraph("Rapport genere automatiquement par HealthCare+."));




//
//
//        document.add(new Paragraph());
//        document.add(new Paragraph("Liste des Medecins"));
//        document.add(new Paragraph(" "));
//        document.add(new Paragraph(
//                "Medecin : " +
//                        patient.getNom() + " " + patient.getPrenom()));
//        document.add(new Paragraph(" "));
//
//        PdfPTable table = new PdfPTable(3);
//
//        table.addCell("Rendez-Vous ID");
//        table.addCell("Date");
//        table.addCell("Patient");
//        table.addCell("Medecin");
//        table.addCell("Statut");
//
//        for (RendezVous rdv : list) {
//
//            table.addCell(rdv.getId().toString());
//            table.addCell(String.valueOf(rdv.getDateRendezVous()));
//            table.addCell(rdv.getPatient().toString());
//            table.addCell(rdv.getMedecin().toString());
//            table.addCell(rdv.getStatut().toString());
//        }
//
//        document.add(table);
        document.close();

        return out.toByteArray();
    }

}
