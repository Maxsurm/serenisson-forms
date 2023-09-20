package formulairesClient.repositories;

import formulairesClient.entities.Patient;
import formulairesClient.entities.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByNomContaining(String nom, Pageable pageable);
    long countByNomContaining(String nom);

    Patient findByMail(String mail);

}
