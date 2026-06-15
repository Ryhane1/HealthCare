package org.example.healthcare.Repository;

import org.example.healthcare.Model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Override
    @EntityGraph(attributePaths = {"dossierMedical"})
    Page<Patient> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"dossierMedical"})
    Page<Patient> findByNomContains(String mot, Pageable pageable);

    @EntityGraph(attributePaths = {"dossierMedical"})
    Patient findByNom(String nom);


}
