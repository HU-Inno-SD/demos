package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ContactId implements Serializable {


    @Column(columnDefinition = "uuid") //Deze columndefinitie is een H2-specifieke bug-workaround :S
    private UUID id; //https://github.com/quarkusio/quarkus/discussions/25535

    protected ContactId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactId contactId = (ContactId) o;
        return id.equals(contactId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
