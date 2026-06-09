package org.example.healthcare.Repository;

import org.example.healthcare.Model.DossierMedical;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {


//    Page<DossierMedical> findByPatientId(Long patientId, Pageable pageable);
        DossierMedical findByPatientId(Long patientId);
}
