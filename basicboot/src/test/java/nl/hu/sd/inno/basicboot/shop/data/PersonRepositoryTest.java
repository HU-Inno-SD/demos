package nl.hu.sd.inno.basicboot.shop.data;

import nl.hu.sd.inno.basicboot.shop.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository persons;

    @Test
    public void canFindPersonByName() {
        Person p = new Person("Tom");
        persons.save(p);

        Person result = persons.findByName("Tom").orElseThrow();
        assertEquals(p, result);
    }

}
