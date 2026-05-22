package org.example.healthcare.Repository;

import org.example.healthcare.Model.Medecin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    Page<Medecin> findBySpecialite(String specialite, Pageable pageable);

    Medecin findByNom(String nom);
}
