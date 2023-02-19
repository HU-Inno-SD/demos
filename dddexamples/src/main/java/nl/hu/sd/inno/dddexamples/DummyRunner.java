package nl.hu.sd.inno.dddexamples;

import nl.hu.sd.inno.dddexamples.crm.alternativeid.AlternativeContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DummyRunner implements CommandLineRunner {

    @Autowired
    private AlternativeContactRepository repo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello World");
    }
}
