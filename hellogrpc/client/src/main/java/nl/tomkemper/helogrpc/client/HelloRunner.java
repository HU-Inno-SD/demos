package nl.tomkemper.helogrpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloRunner implements CommandLineRunner {

    @Autowired
    private HelloService service;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.service.sayHello("World"));
    }
}
