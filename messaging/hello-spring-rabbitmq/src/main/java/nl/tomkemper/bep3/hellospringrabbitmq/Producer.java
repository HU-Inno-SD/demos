package nl.tomkemper.bep3.hellospringrabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate template;

    public Producer(RabbitTemplate template){
        this.template = template;
    }

    public void sendMessage(SomeMessage message){
        this.template.convertAndSend("demo-queue", message);
    }
}
