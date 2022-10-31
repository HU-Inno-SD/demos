package nl.tomkemper.bep3.hellorabbitmq.direct;


import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import nl.tomkemper.bep3.hellorabbitmq.LoggingConsumer;
import nl.tomkemper.bep3.hellorabbitmq.LoggingReturnListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Direct {
    public static final String EXCHANGE = "hello-direct";

    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();

        try (Connection c = cf.newConnection()) {
            try (Channel channel = c.createChannel()) {
                channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT);
                channel.queueDeclare("queue1", false, false, false, null);
                channel.queueDeclare("queue2", false, false, false, null);
                channel.queueDeclare("queue3", false, false, false, null);

                channel.addReturnListener(new LoggingReturnListener());

                channel.queueBind("queue1", EXCHANGE, "all");
                channel.queueBind("queue2", EXCHANGE, "all");

                channel.basicPublish(EXCHANGE, "all", null, "Hai".getBytes());
                channel.basicConsume("queue1", true, new LoggingConsumer());
                channel.basicConsume("queue2", true, new LoggingConsumer());

                channel.basicPublish(EXCHANGE, "queue3", true,null, "Deze komt nooit aan".getBytes());
                channel.basicConsume("queue3", true, new LoggingConsumer());
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }

}
