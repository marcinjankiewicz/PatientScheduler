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
public class PhysiotherapistRepoTest extends BaseData {
    @Autowired
    private PhysiotherapistRepo physiotherapistRepo;

    @Test
    public void createPhysiotherapistAndCheckIfIsInDB(){
        physiotherapistRepo.save(physiotherapist1);
        physiotherapistRepo.findPhysiotherapistByFirstName(physiotherapist1.getFirstName()).forEach(physio ->{
            assertEquals(physio.getFirstName(), physiotherapist1.getFirstName());
        });
    }
}
