package org.example.healthcare.Repository;

import org.example.healthcare.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

//    @Query("select p from patient ")
//    List<Patient> trouverAvecdiagnostic (String motCle);
}
