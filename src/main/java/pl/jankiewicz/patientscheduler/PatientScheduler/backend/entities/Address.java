package pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.AbstractEntity;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.User;


import javax.persistence.*;
import javax.validation.constraints.Size;

import static pl.jankiewicz.patientscheduler.PatientScheduler.backend.features.ValidationMessages.NOT_PROPER_LENGTH;

@Entity
@Getter
@Setter
public class Address extends AbstractEntity {
    @Size(min = 2, max = 20, message = "Pole 'Nazwa adresu'" + NOT_PROPER_LENGTH)
    private String name;
    @Size(min = 2, max = 30, message = "Pole 'Miasto'" + NOT_PROPER_LENGTH)
    private String city;
    private String postalCode;
    @Size(min = 2, max = 50, message = "Pole 'Ulica'" + NOT_PROPER_LENGTH)
    private String street;
    private String number;
    @ManyToOne(targetEntity = Therapist.class)
    private Therapist therapist;
}
