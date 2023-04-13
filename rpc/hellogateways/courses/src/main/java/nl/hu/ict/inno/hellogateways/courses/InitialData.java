package nl.hu.ict.inno.hellogateways.courses;

import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InitialData implements CommandLineRunner {

    private final EntityManager entities;

    public InitialData(EntityManager entities) {
        this.entities = entities;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Course programming = new Course("PROG", "Programming", CourseLevel.Freshman);
        Course BEP1 = new Course("BEP1", "Backend Programming 1", CourseLevel.Freshman, List.of(programming));
        Course DataPersist = new Course("D&P", "Data & Persistency", CourseLevel.Sophomore, List.of(programming));
        Course BEP2 = new Course("BEP2", "Backend Programming 2", CourseLevel.Sophomore, List.of(BEP1, DataPersist));
        Course inno = new Course("INNO-SD", "INNO SD Backend Kennisroute", CourseLevel.Bachelors, ECTS.semester(), List.of(BEP2));

        this.entities.persist(inno);
    }
}
