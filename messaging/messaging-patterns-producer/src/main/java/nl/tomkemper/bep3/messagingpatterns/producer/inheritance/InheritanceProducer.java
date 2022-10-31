package nl.tomkemper.bep3.messagingpatterns.producer.inheritance;


import nl.tomkemper.bep3.messagingpatterns.producer.fireforget.SendEmailCommand;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class InheritanceProducer {
    private RabbitTemplate rabbit;

    public InheritanceProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendMessage(EmailChangedEvent message) {
        this.rabbit.convertAndSend("inheritance-example", message);
    }

    public void sendMessage(FingersChoppedOffEvent message) {
        this.rabbit.convertAndSend("inheritance-example", message);
    }
}
