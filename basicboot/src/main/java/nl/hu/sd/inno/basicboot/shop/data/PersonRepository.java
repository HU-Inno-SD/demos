package nl.hu.sd.inno.basicboot.shop.data;

import nl.hu.sd.inno.basicboot.shop.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByName(String name);
}
