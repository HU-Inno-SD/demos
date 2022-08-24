package nl.hu.sd.inno.basicboot.data;

import nl.hu.sd.inno.basicboot.domain.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {
}
