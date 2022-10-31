package nl.tomkemper.bep3.hellorabbitmq.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import nl.tomkemper.bep3.hellorabbitmq.LoggingConsumer;
import nl.tomkemper.bep3.hellorabbitmq.LoggingReturnListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Fanout {
    public static final String EXCHANGE = "hello-fanout";

    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();

        try (Connection c = cf.newConnection()) {
            try (Channel channel = c.createChannel()) {
                channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT);
                channel.queueDeclare("queue-fan-1", false, false, false, null);
                channel.queueDeclare("queue-fan-2", false, false, false, null);
                channel.queueDeclare("queue-fan-3", false, false, false, null);

                channel.addReturnListener(new LoggingReturnListener());

                channel.queueBind("queue-fan-1", EXCHANGE, "matters_not");
                channel.queueBind("queue-fan-2", EXCHANGE, "not-null... rrrrreaally?");

                channel.basicPublish(EXCHANGE, "tralala", null, "Hai".getBytes());
                channel.basicConsume("queue-fan-1", true, new LoggingConsumer());
                channel.basicConsume("queue-fan-2", true, new LoggingConsumer());

            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

}
