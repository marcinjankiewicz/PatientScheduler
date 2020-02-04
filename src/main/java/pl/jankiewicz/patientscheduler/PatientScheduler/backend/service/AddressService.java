package pl.jankiewicz.patientscheduler.PatientScheduler.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Address;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.AddressRepo;

@Service
public class AddressService implements CrudService<Address> {
    private AddressRepo addressRepo;

    @Autowired
    public AddressService(AddressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    @Override
    public AddressRepo getRepository() {
        return addressRepo;
    }

    @Override
    public Address createNew() {
        return new Address();
    }
}
