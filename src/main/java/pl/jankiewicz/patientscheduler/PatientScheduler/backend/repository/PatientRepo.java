package pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Patient;

import java.util.List;

public interface PatientRepo extends JpaRepository<Patient, Long> {

    List<Patient> findPatientByFirstName(String firstName);

    Page<Patient> findBy(Pageable pageable);

    Patient findByEmailIgnoreCase(String email);

    Page<Patient> findByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(
            String emailLike, String firstNameLike, String lastNameLike, Pageable pageable);

    long countByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(
            String emailLike, String firstNameLike, String lastNameLike);

}
