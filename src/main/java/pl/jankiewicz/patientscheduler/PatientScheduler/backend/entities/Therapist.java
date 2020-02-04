package pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities;

import lombok.Getter;
import lombok.Setter;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Address;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
public class Therapist extends User {
    @OneToMany(mappedBy = "therapist")
    protected List<Address> address;

    @ManyToMany
    @JoinTable(name = "patient_therapist", joinColumns = @JoinColumn(name = "therapist_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"))
    private List<Patient> patients;
    @OneToMany(mappedBy = "therapist")
    private List<Appointment> appointments;

    public Therapist() {
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }



}
