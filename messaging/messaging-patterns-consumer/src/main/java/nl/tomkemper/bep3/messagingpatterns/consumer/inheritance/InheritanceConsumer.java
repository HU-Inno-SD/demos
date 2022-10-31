package nl.tomkemper.bep3.messagingpatterns.consumer.inheritance;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InheritanceConsumer {
    @RabbitListener(queues = {"inheritance-example"})
    public void handleEvent(Event e){
        e.print();
    }
}
