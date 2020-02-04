package pl.jankiewicz.patientscheduler.PatientScheduler.backend.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.BaseData;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Patient;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Physiotherapist;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest extends BaseData {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PhysiotherapistService physiotherapistService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AppointmentService appointmentService;

    @Test
    public void create() {
        Physiotherapist physiotherapistDB = physiotherapistService.save(physiotherapist1);
        Patient patientDB = patientService.save(patient1);
        if (patientDB != null) {
            physiotherapist1.getPatients().add(patientDB);
            physiotherapistService.save(physiotherapist1);
        }
        if(physiotherapistDB != null){
            address.setTherapist(physiotherapistDB);
            addressService.save(address);
        }
        if(patientDB != null && physiotherapistDB != null){
            appointment1.setPatient(patientDB);
            appointment1.setTherapist(physiotherapistDB);
            appointmentService.save(appointment1);
        }
    }

    @Test
    public void findPhysiotherapistByFirstName(){
        Physiotherapist physiotherapist = physiotherapistService.findByFirstName("Robert").get(0);
        assertEquals("Robert", physiotherapist.getFirstName());
    }
}
