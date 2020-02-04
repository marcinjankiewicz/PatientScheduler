package pl.jankiewicz.patientscheduler.PatientScheduler.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Therapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.User;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.TherapistRepo;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final TherapistRepo therapistRepo;

    @Autowired
    public UserService(TherapistRepo userRepo) {
        this.therapistRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Therapist> optUser = therapistRepo.findByEmailLikeIgnoreCase(username);
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        } else {
            return optUser.get();
        }
    }

    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<Therapist> optUser = therapistRepo.findByEmailLikeIgnoreCase(email);
        if (optUser.isEmpty()) {
            throw new UsernameNotFoundException("No user present with email: " + email);
        } else {
            return optUser.get();
        }
    }

    public User load(Long id) {
        return therapistRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
