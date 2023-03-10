package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

@Embeddable
public class Email {
    @Column(name = "email")
    private String value;

    protected Email() {

    }

    public static final Pattern EMAILPATTERN = Pattern.compile("^(.+)@(\\S+)$");

    public Email(String email) {
        if (EMAILPATTERN.matcher(email).matches()) {
            this.value = email;
        } else {
            throw new IllegalArgumentException(email + " is not a valid emailaddress");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
