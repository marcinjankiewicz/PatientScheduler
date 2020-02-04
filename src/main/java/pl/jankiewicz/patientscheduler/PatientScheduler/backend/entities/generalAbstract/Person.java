package pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

import static pl.jankiewicz.patientscheduler.PatientScheduler.backend.features.ValidationMessages.*;

@MappedSuperclass
@Setter
@Getter
@NoArgsConstructor
public abstract class Person extends AbstractEntity{

    @NotEmpty(message = "Pole 'Imię'" + NOT_EMPTY)
    @Size(min = 2, max = 50, message = "Pole 'Imię'" + NOT_PROPER_LENGTH)
    protected String firstName;
    protected String lastName;
    @Column(unique = true)
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = WRONG_FORMAT_PHONE_NUMBER)
    protected String phoneNumber;
    @Email(message = "Niepoprawny format adresu e-mail")
    @Column(unique = true)
    protected String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return phoneNumber == person.phoneNumber &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + firstName + '\'' +
                ", surname='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
