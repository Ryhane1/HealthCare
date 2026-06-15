package org.example.healthcare.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.healthcare.DTOs.DossierMedicalDTO;
import org.example.healthcare.Mappers.DossierMedicalMapper;
import org.example.healthcare.Model.DossierMedical;
import org.example.healthcare.Repository.DossierMedicalRepository;
import org.example.healthcare.Repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final DossierMedicalMapper dossierMedicalMapper;
    private final PatientRepository patientRepository;

    @CacheEvict(value = "dossierMedicalByPatient", key = "#dossierMedicalDTO.patientId")
    public DossierMedicalDTO creerDossierMedical(DossierMedicalDTO dossierMedicalDTO){
        DossierMedical dossier = dossierMedicalMapper.toEntity(dossierMedicalDTO);
        dossier.setDateCreation(LocalDate.now());
        dossier.setPatient(patientRepository.findById(dossierMedicalDTO.getPatientId()).orElseThrow());
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.save(dossier));
    }

    @CacheEvict(value = "dossierMedicalByPatient", key = "#id")
    public DossierMedicalDTO ajouterDiagnostic (Long id , String diagnostic){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow();
        dossierMedical.setDiagnostic(diagnostic);
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.save(dossierMedical));
    }

    @CacheEvict(value = "dossierMedicalByPatient", key = "#id")
    public DossierMedicalDTO ajouterObservation (Long id , String observation){
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow();
        dossierMedical.setObservation(observation);
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.save(dossierMedical));
    }

    @Cacheable(value = "dossierMedical", key = "#id")
    public DossierMedicalDTO consulterDossierMedical(Long id){
        return dossierMedicalMapper.toDTo(dossierMedicalRepository.findById(id).orElseThrow());
    }

    @Cacheable(value = "dossierMedicals", key = "#pageable.pageNumber + '-' + #pageable.pageSize + '-' + #pageable.sort")
    public Page<DossierMedicalDTO> consulterAllDossierMedical(Pageable pageable){
         Page<DossierMedical> dossierList = dossierMedicalRepository.findAll(pageable);
         return dossierList.map(dossierMedicalMapper::toDTo);
    }

    @Cacheable(value = "dossierMedicalByPatient", key = "#patientId")
    public DossierMedicalDTO consulterDossierMedicalParPatient(Long patientId){
        DossierMedical dossierList = dossierMedicalRepository.findByPatientId(patientId);
        return dossierMedicalMapper.toDTo(dossierList);
    }


    public byte[] genererPDF(Long id) throws Exception{
        DossierMedical dossierMedical = dossierMedicalRepository.findById(id).orElseThrow();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Document document = new Document();

        PdfWriter.getInstance(document, out);

        document.open();

        document.add(new Paragraph("Dossier Médical"));

        document.add(
                new Paragraph("Patient : "
                        + dossierMedical.getPatient().getNom()));

        document.add(
                new Paragraph("Diagnostic : "
                        + dossierMedical.getDiagnostic()));
        document.add(
                new Paragraph("Observation : "+ dossierMedical.getObservation()));
        document.add(
                new Paragraph("Date de Creation : " + dossierMedical.getDateCreation()));


        document.close();

        return out.toByteArray();
    }

//        PdfWriter writer = new PdfWriter(out);
//
//        PdfDocument pd = new PdfDocument(writer);
//
//        Document document = new Document(pdf);


}
