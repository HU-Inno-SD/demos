package nl.hu.sd.inno.dddexamples.crm.noddd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ContactPersistenceTest {

    @Autowired
    private EntityManager entities;

    @Test
    public void canPersistContact() {
        Contact c = new Contact();
        c.setCity("Utrecht");
        c.setHouseNumber("42B");
        c.setFirstName("Test");

        this.entities.persist(c);
        this.entities.flush();
        this.entities.clear();

        assertEquals(c, this.entities.find(Contact.class, c.getId()));
    }

    //@Test Hier zie je een voorbeeldje van een nadeel van die equals, dat is -meestal- niet de moeite om te fixen...
    public void downsideOfEqualsId() {
        Contact c = new Contact();
        c.setFirstName("Test");

        HashSet<Contact> set = new HashSet<>();
        set.add(c);

        this.entities.persist(c);

        assertTrue(set.contains(c));
    }
}
