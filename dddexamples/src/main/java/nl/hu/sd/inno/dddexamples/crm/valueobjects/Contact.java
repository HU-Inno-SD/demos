package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name="ContactValues")
public class Contact {
    @EmbeddedId //Een value object als ID is niet zo lastig, maar een GeneratedValue wel... dus omzeilen we dat maar met UUIDs
    @Embedded
    private ContactId id;

    protected Contact() {

    }

    public Contact(Name name, Address address, ContactDetails details) {
        this.id = new ContactId();
        this.name = name;
        this.address = address;
        this.contactDetails = details;
    }

    @Embedded
    private Name name;

    @Embedded
    private Address address;

    @Embedded
    private ContactDetails contactDetails;


    public Name getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public ContactId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact3 = (Contact) o;
        return id.equals(contact3.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
