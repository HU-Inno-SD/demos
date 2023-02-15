package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import javax.persistence.Embeddable;
import java.util.Objects;

// Deze code kan in recentere Java versies vervangen worden door:
//@Embeddable
//public record Address(String street, String housenr, String ciy) {
//}
// Dus doen we in de andere files ook

@Embeddable
public class Address {


    private String street;
    private String housenr;
    private String city;

    protected Address() {
    }

    public Address(String street, String housenr, String city) {
        //Weggelaten: null-checks en overige validatie
        this.street = street;
        this.housenr = housenr;
        this.city = city;
    }


    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHousenr() {
        return housenr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && housenr.equals(address.housenr) && city.equals(address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, housenr, city);
    }
}
