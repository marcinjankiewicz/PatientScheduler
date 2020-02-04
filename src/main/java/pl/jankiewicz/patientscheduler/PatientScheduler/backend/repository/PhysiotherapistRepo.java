package pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Physiotherapist;

import java.util.List;
import java.util.Optional;

public interface PhysiotherapistRepo extends JpaRepository<Physiotherapist, Long> {
    List<Physiotherapist> findPhysiotherapistByFirstName(String firstName);

    Page<Physiotherapist> findByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(
            String emailLike, String firstNameLike, String lastNameLike, Pageable pageable);

    Optional<Physiotherapist> findByEmailLikeIgnoreCase(String emailLike);

    long countByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(
            String emailLike, String firstNameLike, String lastNameLike);

    Page<Physiotherapist> findBy(Pageable pageable);
}
