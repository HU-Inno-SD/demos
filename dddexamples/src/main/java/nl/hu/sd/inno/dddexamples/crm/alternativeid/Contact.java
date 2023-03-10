package nl.hu.sd.inno.dddexamples.crm.alternativeid;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.Objects;

//Deze aanpak is niet 'de fraaiste', maar hij gebruikt gewoon geen ingewikkelde features van het framework. En van buitenaf
//werkt het nagenoeg hetzelfde. Dit is handig als je merkt dat je in de andere versie vooral tijd aan het verspillen bent
//met worstelen met JPA. De waarde van je werk zit in een mooi domeinmodel. Framework X, Y of Z daar beter voor inzetten
//kan ook altijd later...
@Entity(name = "ContactAltId")
public class Contact {

    @GeneratedValue
    @Id
    private Long id;

    private String firstName;
    private String initials;
    private String surName;

    private String emailaddress;
    private String phonenumber;

    private String street;
    private String houseNumber;
    private String city;
    private String zipcode;

    protected Contact(){

    }

    public Contact(Name name, Address address, ContactDetails details) {
        this.firstName = name.firstname();
        this.initials = name.initials();
        this.surName = name.surname();

        this.street = address.street();
        this.houseNumber = address.housenr();
        this.city = address.city();
        this.zipcode = address.zipcode();

        this.emailaddress = details.email().value();
        this.phonenumber = details.phonenr().value();
    }


    public Name getName() {
        return new Name(this.firstName, this.initials, this.surName);
    }

    public Address getAddress() {
        return new Address(this.street, this.houseNumber, this.city, this.zipcode);
    }

    public ContactDetails getContactDetails() {
        return new ContactDetails(new Email(this.emailaddress), new PhoneNr(this.phonenumber));
    }

    public ContactId getId() {
        return new ContactId(id);
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
