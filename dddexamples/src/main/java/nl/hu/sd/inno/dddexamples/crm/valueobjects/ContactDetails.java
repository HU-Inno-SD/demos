package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.Objects;

@Embeddable
public class ContactDetails {
    protected ContactDetails(){

    }

    @Embedded
    private Email email;

    @Embedded
    private PhoneNr phone;

    public ContactDetails(Email email, PhoneNr phone){
        this.email = email;
        this.phone = phone;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactDetails that = (ContactDetails) o;
        return email.equals(that.email) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, phone);
    }
}
