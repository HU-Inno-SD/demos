package nl.tomkemper.bep3.messagingpatterns.consumer.pubsub;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PubSubConsumer2 {

    @RabbitListener(queues = {"happynewyear2-example"})
    public void happyNewYear(HappyNewYearEvent message) {
        System.out.printf("Listener 2 zeg gelukkig %d^%n", message.year);
    }
}
