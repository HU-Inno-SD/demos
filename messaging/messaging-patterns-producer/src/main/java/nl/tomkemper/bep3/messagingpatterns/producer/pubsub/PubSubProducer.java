package nl.tomkemper.bep3.messagingpatterns.producer.pubsub;

import nl.tomkemper.bep3.messagingpatterns.producer.fireforget.SendEmailCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class PubSubProducer {
    private RabbitTemplate rabbit;

    public PubSubProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendMessage(HappyNewYearEvent event) {
        this.rabbit.convertAndSend("pubsubdemo", "happy-new-year", HappyNewYearEvent.of(2022));
    }
}
