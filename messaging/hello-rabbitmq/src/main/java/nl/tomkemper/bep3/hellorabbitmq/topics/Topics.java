package nl.tomkemper.bep3.hellorabbitmq.topics;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import nl.tomkemper.bep3.hellorabbitmq.LoggingConsumer;
import nl.tomkemper.bep3.hellorabbitmq.LoggingReturnListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Topics {
    public static final String EXCHANGE = "hello-topics";

    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();

        try (Connection c = cf.newConnection()) {
            try (Channel channel = c.createChannel()) {
                channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.TOPIC);
                channel.queueDeclare("queue-topic-1", false, false, false, null);
                channel.queueDeclare("queue-topic-2", false, false, false, null);
                channel.queueDeclare("queue-topic-3", false, false, false, null);
                channel.queueDeclare("queue-topic-4", false, false, false, null);
                channel.queueDeclare("queue-topic-5", false, false, false, null);

                channel.addReturnListener(new LoggingReturnListener());

                channel.queueBind("queue-topic-1", EXCHANGE, "#");
                channel.queueBind("queue-topic-2", EXCHANGE, "dieren.*");
                channel.queueBind("queue-topic-3", EXCHANGE, "dieren.gevaarlijk");
                channel.queueBind("queue-topic-4", EXCHANGE, "dieren.lief");
                channel.queueBind("queue-topic-5", EXCHANGE, "eten.*");

                channel.basicPublish(EXCHANGE, "zomaar", null, "Hai allemaal".getBytes());
                channel.basicPublish(EXCHANGE, "dieren.algemeen", null, "Hai dieren".getBytes());
                channel.basicPublish(EXCHANGE, "dieren.gevaarlijk", null, "Hai tijger!".getBytes());
                channel.basicPublish(EXCHANGE, "dieren.lief", null, "Hai konijntje!".getBytes());
                channel.basicPublish(EXCHANGE, "eten.lekker", null, "Biefstuk!".getBytes());

                channel.basicConsume("queue-topic-1", true, new LoggingConsumer("alles"));
                channel.basicConsume("queue-topic-2", true, new LoggingConsumer("dieren"));
                channel.basicConsume("queue-topic-3", true, new LoggingConsumer("pas op!"));
                channel.basicConsume("queue-topic-4", true, new LoggingConsumer("awwww"));
                channel.basicConsume("queue-topic-5", true, new LoggingConsumer("yam yam"));
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}
