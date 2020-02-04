package pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Appointment;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
