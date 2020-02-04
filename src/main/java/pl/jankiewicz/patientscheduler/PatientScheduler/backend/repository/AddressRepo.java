package pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
