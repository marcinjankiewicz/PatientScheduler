package pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Therapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.User;

import java.util.Optional;

public interface TherapistRepo extends JpaRepository<Therapist, Long> {
    Optional<Therapist> findByEmailLikeIgnoreCase(String email);
}
