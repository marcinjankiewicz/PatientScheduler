package pl.jankiewicz.patientscheduler.PatientScheduler.app.security.utils;


import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.User;

@FunctionalInterface
public interface CurrentUser {
    User getUser();
}
