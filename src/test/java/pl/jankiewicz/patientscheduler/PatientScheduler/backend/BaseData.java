package pl.jankiewicz.patientscheduler.PatientScheduler.backend;

import org.junit.Before;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Address;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Appointment;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Patient;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Physiotherapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.features.AppointmentDuration;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.features.Role;

import java.sql.Timestamp;
import java.util.Date;

public class BaseData {

    protected Patient patient1;
    protected Physiotherapist physiotherapist1;
    protected Appointment appointment1;
    protected Address address;

    @Before
    public void setup() {
        this.patient1 = new Patient();
        patient1.setFirstName("Marcin");
        patient1.setLastName("Jankiewicz");
        patient1.setPhoneNumber("500270639");
        patient1.setEmail("wp@wp.pl");
        this.address = new Address();
        address.setCity("Gdańsk");
        address.setNumber("5/22");
        address.setPostalCode("80-177");
        address.setStreet("Żurawia");
        this.physiotherapist1 = new Physiotherapist();
        physiotherapist1.setFirstName("Robert");
        physiotherapist1.setLastName("Adamowski");
        physiotherapist1.setPhoneNumber("422454214");
        physiotherapist1.setEmail("sp@vp.pl");
        physiotherapist1.setPassword("Pass123");
        physiotherapist1.setRole(Role.ADMIN);
        this.appointment1 = new Appointment();
        appointment1.setNotes("Bolace plecy");
        appointment1.setTimestamp(new Timestamp(new Date().getTime()));
        appointment1.setDuration(AppointmentDuration.MINUTES_60);
    }
}
