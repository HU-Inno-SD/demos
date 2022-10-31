package nl.tomkemper.bep3.messagingpatterns.producer.fireforget;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class FireForgetProducer {
    private RabbitTemplate rabbit;

    public FireForgetProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendMessage(SendEmailCommand message) {
        this.rabbit.convertAndSend("fireforget-example", message);
    }
}
