package pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Physiotherapist extends Therapist {





}
