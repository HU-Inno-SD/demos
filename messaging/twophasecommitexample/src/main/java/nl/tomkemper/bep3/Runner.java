package nl.tomkemper.bep3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    MessageProducer producer;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Startup!");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1 * 1000; i++) {

            try {
                producer.sendMessage("Hello World");
            } catch (Exception ex) {
//                ex.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("DONE: " + (end - start));
    }
}
