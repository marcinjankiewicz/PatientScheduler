package pl.jankiewicz.patientscheduler.PatientScheduler.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.Appointment;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.repository.AppointmentRepo;

@Service
public class AppointmentService implements CrudService<Appointment> {
    private AppointmentRepo appointmentRepo;

    @Autowired
    public AppointmentService(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    @Override
    public AppointmentRepo getRepository() {
        return appointmentRepo;
    }

    @Override
    public Appointment createNew() {
        return new Appointment();
    }
}
