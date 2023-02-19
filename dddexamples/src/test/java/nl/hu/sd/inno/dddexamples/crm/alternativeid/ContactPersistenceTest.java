package nl.hu.sd.inno.dddexamples.crm.alternativeid;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ContactPersistenceTest {

    @Autowired
    private EntityManager entities;


    //@Autowired het is me even niet duidelijk waarom ie niet wil autowiren, maar we leven er maar even mee...
    private AlternativeContactRepository repository;


    private Contact c;

    @BeforeEach
    public void insertTestContact() {
        repository = new JPAContactRepository(this.entities);

        c = new Contact(
                new Name("Tom", "", "Test"),
                new Address("Straat", "12B", "Utrecht", "1234ab"),
                new ContactDetails(new Email("test@example.com"), new PhoneNr("0612345678"))
        );

        this.entities.persist(c);
        this.entities.flush();
        this.entities.clear();
    }

    @Test
    public void canSaveContactWithRepository() {
        Contact another = new Contact(
                new Name("Bob", "", "Test2"),
                new Address("Straat", "12B", "Utrecht", "1234ab"),
                new ContactDetails(new Email("test2@example.com"), new PhoneNr("0612345678"))
        );
        this.repository.persist(another);
        this.entities.flush();
        this.entities.clear();

        Contact found = this.repository.findById(another.getId()).orElseThrow();
        assertEquals(another, found);
    }

    @Test
    public void canPersistContact() {
        ContactId id = c.getId();
        assertEquals(c, this.repository.findById(id).orElseThrow());
    }

    @Test
    public void canPersistContactList() {
        List<Contact> results = this.repository.findAll();
        assertEquals(1, results.size());
        assertEquals(c, results.get(0));
    }
}
