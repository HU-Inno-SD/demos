package nl.hu.sd.inno.dddexamples.crm.valueobjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ContactPersistenceTest {

    @Autowired
    private EntityManager entities;


    private Contact c;
    @BeforeEach
    public void insertTestContact(){
        c = new Contact(
                new Name("Tom", "Test"),
                new Address("Straat", "12B", "Utrecht"),
                new ContactDetails(new Email("test@example.com"), new PhoneNr("0612345678"))
        );

        this.entities.persist(c);
        this.entities.flush();
        this.entities.clear();
    }

    @Test
    public void canPersistContact() {
        ContactId id = c.getId();
        assertEquals(c, this.entities.find(Contact.class, id));
    }

    @Test
    public void canPersistContactList() {
        List<Contact> results = this.entities.createQuery("select c from ContactValues c", Contact.class).getResultList();
        assertEquals(1, results.size());
        assertEquals(c, results.get(0));
    }
}
