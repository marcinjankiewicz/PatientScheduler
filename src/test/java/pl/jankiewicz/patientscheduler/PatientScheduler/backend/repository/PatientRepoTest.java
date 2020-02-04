package pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.BaseData;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientRepoTest extends BaseData {
    @Autowired
    private PatientRepo patientRepo;

    @Test
    public void createPatientAndCheckIfIsInDB() {
        patientRepo.save(patient1);
        patientRepo.findPatientByFirstName(patient1.getFirstName()).forEach(patient ->  {
            assertEquals(patient.getFirstName(), patient1.getFirstName());
        });
    }
}
