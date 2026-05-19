package org.example.healthcare.Repository;

import org.example.healthcare.Model.RendezVous;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    Page<RendezVous> findByPatient_Id(Long patientId , Pageable pageable);

    @Query("SELECT r FROM RendezVous r WHERE r.medecin.id = :medecinId")
    Page<RendezVous> findByMedecinId(@Param("medecinId") Long medecinId, Pageable pageable);

    Page<RendezVous> findByDateRendezVous(LocalDate date, Pageable pageable);
}
