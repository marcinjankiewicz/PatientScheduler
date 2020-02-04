package pl.jankiewicz.patientscheduler.PatientScheduler.backend.entities.generalAbstract;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class User extends Person implements UserDetails {
//    @NotEmpty(message = "Please provide your password")
//    @Length(min = 6, message = "Password must have at least 6 characters")
    protected String password;

    protected int active;
    protected String role;

    @Override
    public String getUsername() {
        return email.toLowerCase();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(this.role);
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active == 1;
    }

    @Override
    public String toString() {
        return "User{" +
                "active=" + active +
                ", role='" + role + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return active == user.active &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), active, role);
    }
}
