package pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.BaseData;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Patient;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Therapist;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReposTest extends BaseData {
    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private PhysiotherapistRepo physiotherapistRepo;
    @Autowired
    private AppointmentRepo appointmentRepo;
    @Autowired
    private AddressRepo addressRepo;

    @Test
    public void createAppointment(){
        patientRepo.save(patient1);
        Patient patientFromDB = patientRepo.getOne(1L);
        physiotherapist1.setPatients(Arrays.asList(patientFromDB));
        physiotherapistRepo.save(physiotherapist1);
        Therapist physiotherapistFromDB = physiotherapistRepo.getOne(1L);
        address.setTherapist(physiotherapistFromDB);
        addressRepo.save(address);
        appointment1.setPatient(patientFromDB);
        appointment1.setTherapist(physiotherapistFromDB);
        appointmentRepo.save(appointment1);
    }
}
