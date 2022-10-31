package nl.tomkemper.bep3.whutsupp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static nl.tomkemper.bep3.whutsupp.Whutsupp.*;

@Component
public class IncomingListener {

    private final RabbitTemplate rabbitTemplate;

    public IncomingListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = INCOMING_QUEUE)
    public void processIncoming(ChatMessage incoming) {
        System.out.println("Coming in:" + incoming.getContent());

        if (incoming.getReceiverId() == null) {
            this.rabbitTemplate.convertAndSend(ANNOUNCE_EXCHANGE, "...", incoming);
        } else {
            this.rabbitTemplate.convertAndSend(PM_EXCHANGE, Student.getRoutingKey(incoming.getReceiverId()), incoming);
        }
    }
}
