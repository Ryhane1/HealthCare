package org.example.healthcare.Repository;

import org.example.healthcare.Model.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAll(Pageable pageable);

    Page<Patient> findByNomContains(String mot, Pageable pageable);

    Patient findByNom(String nom);


}
