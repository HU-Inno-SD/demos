package nl.tomkemper.bep3.hellorabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import nl.tomkemper.bep3.hellorabbitmq.LoggingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

@Component
@Order(2)
public class HelloListener implements CommandLineRunner {

    @Override
    public void run(String... args) {
        Thread t = new Thread(this::run);
        t.start();
    }

    private void run() {
        Logger logger = LoggerFactory.getLogger(HelloListener.class);
        logger.info("Starting consumer");
        ConnectionFactory cf = new ConnectionFactory();

        try {
            Connection conn = cf.newConnection(); //TODO: read up on RabbitMQ/Spring lifetimes to properly dispose
            Channel channel = conn.createChannel();

            channel.queueDeclare("Hello2", true, false, false, new HashMap<>());
            channel.basicConsume("Hello2", true, new LoggingConsumer());
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
