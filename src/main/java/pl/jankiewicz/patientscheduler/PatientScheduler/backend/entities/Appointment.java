package pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.AbstractEntity;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract.User;
import pl.jankiewicz.patientscheduler.PatientScheduler.backend.features.AppointmentDuration;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Appointment extends AbstractEntity {
    private Timestamp timestamp;
    @Enumerated(value = EnumType.ORDINAL)
    private AppointmentDuration duration;
    @Column(name = "notes", length = 2500)
    private String notes;
    @ManyToOne
    private Patient patient;
    @ManyToOne(targetEntity = Therapist.class)
    private Therapist therapist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(patient, that.patient) &&
                Objects.equals(therapist, that.therapist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, notes, patient, therapist);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "timestamp=" + timestamp +
                ", notes='" + notes + '\'' +
                ", patient=" + patient +
                ", physiotherapist=" + therapist +
                '}';
    }
}
