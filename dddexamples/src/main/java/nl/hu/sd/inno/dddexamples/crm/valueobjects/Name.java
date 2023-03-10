package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import jakarta.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public class Name {
    private String firstname;
    private String initials;

    private String surname;

    protected Name() {
    }

    public Name(String firstname, String initials, String surname) {
        this.firstname = firstname;
        this.initials = initials;
        this.surname = surname;
    }

    public Name(String firstname, String surname) {
        this(firstname, "", surname);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getInitials() {
        return initials;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return firstname.equals(name.firstname) && initials.equals(name.initials) && surname.equals(name.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, initials, surname);
    }
}
