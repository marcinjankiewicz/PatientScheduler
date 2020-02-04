package pl.jankiewicz.patientscheduler.PatientScheduler.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.jankiewicz.patientscheduler.PatientScheduler.app.security.PasswordEncoderConfiguration;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Physiotherapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.exceptions.UserFriendlyDataException;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.PhysiotherapistRepo;

import java.util.List;
import java.util.Optional;

@Service
public class PhysiotherapistService implements FilterableCrudService<Physiotherapist> {
    private static final String PHYSIOTHERAPIST_ALREADY_EXISTS = "Physiotherapist with such phone number or e-mail already exists";

    private PhysiotherapistRepo physiotherapistRepo;
    private PasswordEncoderConfiguration encoder;

    @Autowired
    public PhysiotherapistService(PhysiotherapistRepo physiotherapistRepo,
                                  PasswordEncoderConfiguration encoder) {
        this.physiotherapistRepo = physiotherapistRepo;
        this.encoder = encoder;
    }

    @Override
    public Page<Physiotherapist> findAnyMatching(Optional<String> filter, Pageable pageable) {
        if (filter.isPresent()) {
            String repositoryFilter = "%" + filter.get() + "%";
            return getRepository().findByEmailLikeIgnoreCaseOrFirstNameLikeIgnoreCaseOrLastNameLikeIgnoreCase(
                    repositoryFilter, repositoryFilter, repositoryFilter, pageable);
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
    public PhysiotherapistRepo getRepository() {
        return physiotherapistRepo;
    }

    @Override
    public Physiotherapist save(Physiotherapist physiotherapist) {
        physiotherapist.setPassword(encoder.passwordEncoder().encode(physiotherapist.getPassword()));
        return saveOrUpdate(physiotherapist);
    }

    @Override
    public Physiotherapist update(Physiotherapist physiotherapist) {
        return saveOrUpdate(physiotherapist);
    }

    private Physiotherapist saveOrUpdate(Physiotherapist physiotherapist) {
        try {
            return FilterableCrudService.super.save(physiotherapist);
        } catch (DataIntegrityViolationException e) {
            throw new UserFriendlyDataException(PHYSIOTHERAPIST_ALREADY_EXISTS);
        }
    }

    @Override
    public Physiotherapist createNew() {
        return new Physiotherapist();
    }

    public Page<Physiotherapist> find(Pageable pageable) {
        return getRepository().findBy(pageable);
    }

    public List<Physiotherapist> findByFirstName(String firstName) {
        if (!firstName.isEmpty()) {
            return physiotherapistRepo.findPhysiotherapistByFirstName(firstName);
        }
        return null;
    }
}
