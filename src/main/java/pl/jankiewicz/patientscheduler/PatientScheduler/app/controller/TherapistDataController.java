package pl.jankiewicz.patientscheduler.PatientScheduler.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Physiotherapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Therapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.User;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.TherapistRepo;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class TherapistDataController {
    private static final String THERAPIST_DATA = "therapistdata";
    private static final String THERAPIST_DATA_FORM = "therapistdataform";

    private final TherapistRepo therapistRepo;
    private String email;
    private List<ObjectError> listOfErrors;

    @GetMapping("/" + THERAPIST_DATA)
    public String getTherapistData(Model model, OAuth2Authentication authentication) {
        this.email = (String) ((LinkedHashMap) ((UsernamePasswordAuthenticationToken) authentication.getUserAuthentication()).getDetails()).get("email");
        fillInput(model, authentication, false);
        return THERAPIST_DATA_FORM;
    }

    private void fillInput(Model model, OAuth2Authentication authentication, boolean isWithErrors) {

        String name = (String) ((LinkedHashMap) ((UsernamePasswordAuthenticationToken) authentication.getUserAuthentication()).getDetails()).get("name");
        String given_name = (String) ((LinkedHashMap) ((UsernamePasswordAuthenticationToken) authentication.getUserAuthentication()).getDetails()).get("given_name");
        therapistRepo.findByEmailLikeIgnoreCase(email).ifPresent(therapist -> {
            if (therapist.getActive() == 1) {
                fillInputFromDB(model);
            } else {
                addBasicDataAttributes(model, name, given_name);
                model.addAttribute("therapist", new Therapist());
            }
        });
        if(isWithErrors){
            List<String> errorMessages = listOfErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
            System.out.println(errorMessages);
            model.addAttribute("errors", errorMessages);
        }
    }

    private void fillInputFromDB(Model model) {
        Therapist therapist = therapistRepo.findByEmailLikeIgnoreCase(email).get();
        addBasicDataAttributes(model, therapist.getFirstName(), therapist.getLastName());
        model.addAttribute("phoneNumber", therapist.getPhoneNumber());
        model.addAttribute("therapist", therapist);
    }

    private void addBasicDataAttributes(Model model, String name, String lastName) {
        model.addAttribute("email", email);
        model.addAttribute("firstName", name);
        model.addAttribute("lastName", lastName);
    }

    @PostMapping("/" + THERAPIST_DATA + "/submit")
    public String doSubmit(@Valid Therapist therapist, BindingResult result, Model model, OAuth2Authentication authentication) {
        if (result.hasErrors()) {
            listOfErrors = result.getAllErrors();
            fillInput(model, authentication, true);
            return THERAPIST_DATA_FORM;
        } else {
            Therapist therapistDB = therapistRepo.findByEmailLikeIgnoreCase(email).orElseThrow();
            therapistDB.setFirstName(therapist.getFirstName());
            therapistDB.setLastName(therapist.getLastName());
            therapistDB.setPhoneNumber(therapist.getPhoneNumber());
            therapistDB.setActive(1);

            therapistRepo.save(therapistDB);
        }
        return "redirect:/" + THERAPIST_DATA;
    }
}
