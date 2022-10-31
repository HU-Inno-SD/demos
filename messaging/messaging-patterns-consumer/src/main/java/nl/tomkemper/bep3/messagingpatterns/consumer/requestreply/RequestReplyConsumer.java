package nl.tomkemper.bep3.messagingpatterns.consumer.requestreply;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RequestReplyConsumer {

    private final Map<String, Integer> counters = new HashMap<>();

    @RabbitListener(queues = {"incrementcounter-example"})
    public void increment(IncrementCommand cmd) {
        System.out.printf("Ik kreeg een increment voor %s%n", cmd.key);
        if (!counters.containsKey(cmd.key)) {
            counters.put(cmd.key, 0);
        }

        counters.put(cmd.key, counters.get(cmd.key) + 1);
    }

    @RabbitListener(queues = {"querycounter-example"})
    public CounterReply findValue(CounterQuery query) {
        System.out.printf("Ik kreeg een query voor %s%n", query.key);
        CounterReply reply = new CounterReply();
        reply.key = query.key;
        reply.count = counters.getOrDefault(query.key, 0);

        return reply;
    }
}
