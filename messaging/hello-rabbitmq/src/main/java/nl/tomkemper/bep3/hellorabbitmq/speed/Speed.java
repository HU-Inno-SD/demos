package nl.tomkemper.bep3.hellorabbitmq.speed;


import com.rabbitmq.client.*;
import nl.tomkemper.bep3.hellorabbitmq.DummyConsumer;
import nl.tomkemper.bep3.hellorabbitmq.LoggingConsumer;
import nl.tomkemper.bep3.hellorabbitmq.LoggingReturnListener;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class Speed {
    public static final String EXCHANGE = "hello-speed";

    private static long toMs(long start, long end) {
        return (end - start) / (1000 * 1000);
    }

    public static void main(String[] args) {
        ConnectionFactory cf = new ConnectionFactory();

        try (Connection c = cf.newConnection()) {
            try (Channel channel = c.createChannel()) {

                channel.exchangeDelete(EXCHANGE);
                channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.DIRECT, true);
                channel.queueDelete("queue-speed-1");
                channel.queueDeclare("queue-speed-1", true, false, false, null);

                channel.addReturnListener(new LoggingReturnListener());

                channel.queueBind("queue-speed-1", EXCHANGE, "queue-speed-1");

                for (int trials = 0; trials < 10; trials++) {
                    long start = System.nanoTime();
                    for (int i = 0; i < 5 * 1000; i++) {
                        channel.basicPublish(EXCHANGE, "queue-speed-1", true, null, "Hai".getBytes());
                    }

                    long afterPublish = System.nanoTime();

                    channel.basicConsume("queue-speed-1", true, new DummyConsumer());
//                    while (true) {
//                        GetResponse r = channel.basicGet("queue-speed-1", true);
//                        if (r == null) {
//                            break;
//                        }
//                    }

                    long afterConsume = System.nanoTime();

                    System.out.println(String.format("Dat duurde %sms/%sms", toMs(start, afterPublish), toMs(afterPublish, afterConsume)));
                }
            }
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }

    }

}
