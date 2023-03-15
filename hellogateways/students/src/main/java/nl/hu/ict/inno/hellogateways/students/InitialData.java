package nl.hu.ict.inno.hellogateways.students;

import jakarta.persistence.EntityManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialData implements CommandLineRunner {

    private final EntityManager entities;

    public InitialData(EntityManager entities){
        this.entities = entities;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Course prog = new Course("PROG", "Programming");
        Course bep1 = new Course("BEP1", "Backend Programming 1");
        Course bep2 = new Course("BEP2", "Backend Programming 2");

        Student tom = new Student("Tom");
        tom.complete(prog);
        tom.enroll(bep1);

        Student mirko = new Student("Mirko");
        mirko.complete(prog);
        mirko.complete(bep1);
        mirko.enroll(bep2);
        
        entities.persist(tom);
        entities.persist(mirko);
    }
}
