package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class PhoneNr {

    @Column(name = "phone")
    private String value;

    protected PhoneNr() {

    }

    public PhoneNr(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNr phoneNr = (PhoneNr) o;
        return value.equals(phoneNr.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
