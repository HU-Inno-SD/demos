package nl.tomkemper.bep3.hellorabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

@Component
@Order(13)
public class HelloPublisher implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(HelloPublisher.class);

    private void run() {
        ConnectionFactory cf = new ConnectionFactory();
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = cf.newConnection()) {
            Channel channel = conn.createChannel();

            channel.queueDeclare("Hello2", true, false, false, new HashMap<>());

            String message = "Hello!";

            int aantalKeer = 10;
            while (aantalKeer > 0) {
                logger.info("Starten met zenden!");
                for (int i = 0; i < aantalKeer; i++) {
                    byte[] bytes = StandardCharsets.UTF_8.encode(message + i).array();
                    channel.basicPublish("", "Hello2", null, bytes);
                }
                logger.info("Poeh hé...");
                logger.info("Nog een keer? Vul een getal in hoe vaak: ");
                String response_raw = scanner.nextLine().trim();

                try {
                    aantalKeer = Integer.parseInt(response_raw);
                } catch (NumberFormatException e) {
                    aantalKeer = -1;
                }
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) {
        Thread t = new Thread(this::run);
        t.start();
    }
}
