package pl.jankiewicz.patientscheduler.PatientScheduler.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Patient;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.exceptions.UserFriendlyDataException;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.PatientRepo;

import java.util.Optional;

@Service
public class PatientService implements FilterableCrudService<Patient> {
    private static final String PATIENT_ALREADY_EXISTS = "Patient with such phone number or e-mail already exists";
    private PatientRepo patientRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    @Override
    public Page<Patient> findAnyMatching(Optional<String> filter, Pageable pageable) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return getRepository().findByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase
                    (repositoryFilter, repositoryFilter, repositoryFilter, pageable);
        } else {
            return find(pageable);
        }
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return getRepository().countByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase
                    (repositoryFilter, repositoryFilter, repositoryFilter);
        } else {
            return count();
        }
    }

    @Override
    public PatientRepo getRepository() {
        return patientRepo;
    }

    @Override
    public Patient createNew() {
        return new Patient();
    }

    @Override
    public Patient save(Patient patient) {
        try {
            return FilterableCrudService.super.save(patient);
        } catch (DataIntegrityViolationException e) {
            throw new UserFriendlyDataException(PATIENT_ALREADY_EXISTS);
        }
    }

    public Page<Patient> find(Pageable pageable) {
        return getRepository().findBy(pageable);
    }
}
