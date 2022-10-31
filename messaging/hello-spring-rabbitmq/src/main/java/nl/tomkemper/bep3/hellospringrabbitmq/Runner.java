package nl.tomkemper.bep3.hellospringrabbitmq;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private Producer producer;

    public Runner(Producer producer){
        this.producer = producer;
    }

    @Override
    public void run(String... args) throws Exception {
        while(true){
            this.producer.sendMessage(new SomeMessage("Hello RabbitMQ"));
            Thread.sleep(5000);
        }

    }
}
