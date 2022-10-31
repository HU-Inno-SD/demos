package nl.tomkemper.bep3.messagingpatterns.producer.competingconsumers;

import nl.tomkemper.bep3.messagingpatterns.producer.fireforget.SendEmailCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class CompetingConsumerProducer {
    private RabbitTemplate rabbit;

    public CompetingConsumerProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendMessage(ProcessImageCommand message) {
        this.rabbit.convertAndSend("competingconsumers-example", message);
    }
}
