package nl.tomkemper.bep3.hellorabbitmq.defaultexchange;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import nl.tomkemper.bep3.hellorabbitmq.LoggingConsumer;
import nl.tomkemper.bep3.hellorabbitmq.LoggingReturnListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();

        try (Connection c = cf.newConnection()) {
            try (Channel channel = c.createChannel()) {

                channel.queueDeclare("somequeue", false, false, false, null);
                String defaultExchange = ""; //Dit is niet heel intuïtief
                String message = "Hello World";
                channel.basicPublish(defaultExchange, "somequeue", null, message.getBytes());

                channel.basicConsume("somequeue", true, new LoggingConsumer());

            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
