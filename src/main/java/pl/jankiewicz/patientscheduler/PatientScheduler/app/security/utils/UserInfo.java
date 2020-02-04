package pl.jankiewicz.patientscheduler.PatientScheduler.app.security.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInfo {
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();


    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }




}
