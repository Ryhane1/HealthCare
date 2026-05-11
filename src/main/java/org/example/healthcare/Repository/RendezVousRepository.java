package org.example.healthcare.Repository;

import org.example.healthcare.Model.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByPatient_Id(Long patientId);

    @Query("SELECT r FROM RendezVous r WHERE r.medecin.id = :medecinId")
    List<RendezVous> findByMedecinId(@Param("medecinId") Long medecinId);

}
