package pl.jankiewicz.patientscheduler.PatientScheduler.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Physiotherapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Therapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.PhysiotherapistRepo;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.TherapistRepo;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final TherapistRepo therapistRepo;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model, OAuth2Authentication authentication, HttpSession session) {
        if (authentication != null && authentication.isAuthenticated()) {
            String pictureUri = (String) ((LinkedHashMap) ((UsernamePasswordAuthenticationToken) authentication.getUserAuthentication()).getDetails()).get("picture");
            String email = (String) ((LinkedHashMap) ((UsernamePasswordAuthenticationToken) authentication.getUserAuthentication()).getDetails()).get("email");
            session.setAttribute("picture", pictureUri);
            saveUserIfNotPresent(authentication, email);
        }
        return "index";
    }

    private void saveUserIfNotPresent(OAuth2Authentication authentication, String email) {
        if (therapistRepo.findByEmailLikeIgnoreCase(email).isEmpty()) {
            String name = (String) ((LinkedHashMap) ((UsernamePasswordAuthenticationToken) authentication.getUserAuthentication()).getDetails()).get("name");
            Therapist therapist = new Therapist();
            therapist.setEmail(email);
            therapist.setFirstName(name);
            therapistRepo.save(therapist);
        }
    }

}
