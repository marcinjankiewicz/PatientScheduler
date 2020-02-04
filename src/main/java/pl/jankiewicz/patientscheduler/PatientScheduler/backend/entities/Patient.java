package pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities;

import lombok.Getter;
import lombok.Setter;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.Person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Patient extends Person {

    @Column(name = "email_reminder")
    private boolean wantsEmailReminders;
    @Column(name = "sms_reminder")
    private boolean wantsSmsReminders;
    @ManyToMany(mappedBy = "patients")
    private List<Physiotherapist> physiotherapists;
    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    public Patient() {
        this.physiotherapists = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }
}
