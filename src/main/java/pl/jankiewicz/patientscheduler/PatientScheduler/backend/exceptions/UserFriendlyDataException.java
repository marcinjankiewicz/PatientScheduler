package pl.jankiewicz.patientscheduler.PatientScheduler.backend.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class UserFriendlyDataException extends DataIntegrityViolationException {
    public UserFriendlyDataException(String message){
        super((message));
    }
}
