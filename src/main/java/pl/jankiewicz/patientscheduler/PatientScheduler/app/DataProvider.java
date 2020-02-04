package pl.jankiewicz.patientscheduler.PatientScheduler.app;



import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Address;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Appointment;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Patient;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Physiotherapist;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.features.AppointmentDuration;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.features.Role;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.service.AddressService;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.service.AppointmentService;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.service.PatientService;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.service.PhysiotherapistService;

import java.sql.Timestamp;
import java.util.Date;

//@Component
public class DataProvider implements HasLogger {
    private PhysiotherapistService physiotherapistService;
    private PatientService patientService;
    private AddressService addressService;
    private AppointmentService appointmentService;

    private Patient patient1;
    private Physiotherapist physiotherapist1;
    private Physiotherapist physiotherapist2;
    private Appointment appointment1;
    private Address address;

//    @Autowired
    public DataProvider(PhysiotherapistService physiotherapistService, PatientService patientService, AddressService addressService,
                        AppointmentService appointmentService) {
        this.physiotherapistService = physiotherapistService;
        this.patientService = patientService;
        this.addressService = addressService;
        this.appointmentService = appointmentService;
        patient1 = new Patient();
        patient1.setFirstName("Marcin");
        patient1.setLastName("Jankiewicz");
        patient1.setPhoneNumber("500270639");
        patient1.setEmail("wp@wp.pl");
        address = new Address();
        address.setCity("Gdańsk");
        address.setNumber("5/22");
        address.setPostalCode("80-177");
        address.setStreet("Żurawia");
        physiotherapist1 = new Physiotherapist();
        physiotherapist1.setFirstName("Robert");
        physiotherapist1.setLastName("Adamowski");
        physiotherapist1.setPhoneNumber("422454214");
        physiotherapist1.setEmail("sp@vp.pl");
        physiotherapist1.setPassword("Pass123");
        physiotherapist1.setRole(Role.ADMIN);
        physiotherapist1.setActive(1);

        physiotherapist2 = new Physiotherapist();
        physiotherapist2.setFirstName("Adam");
        physiotherapist2.setLastName("Dobry");
        physiotherapist2.setPhoneNumber("512154");
        physiotherapist2.setEmail("dobrostaj@gmail.com");
        physiotherapist2.setPassword("qwe123");
        physiotherapist2.setRole(Role.ADMIN);
        physiotherapist2.setActive(1);

        appointment1 = new Appointment();
        appointment1.setNotes("Bolace plecy");
        appointment1.setTimestamp(new Timestamp(new Date().getTime()));
        appointment1.setDuration(AppointmentDuration.MINUTES_60);
        create();
    }

    public void create() {
        Physiotherapist physiotherapistDB = physiotherapistService.save(physiotherapist1);
        physiotherapistService.save(physiotherapist2);
        Patient patientDB = patientService.save(patient1);
        if (patientDB != null) {
            physiotherapist1.getPatients().add(patientDB);
            physiotherapistService.update(physiotherapist1);
        }
        if (physiotherapistDB != null) {
            address.setTherapist(physiotherapistDB);
            addressService.save(address);
        }
        if (patientDB != null && physiotherapistDB != null) {
            appointment1.setPatient(patientDB);
            appointment1.setTherapist(physiotherapistDB);
            appointmentService.save(appointment1);
        }
    }
}
