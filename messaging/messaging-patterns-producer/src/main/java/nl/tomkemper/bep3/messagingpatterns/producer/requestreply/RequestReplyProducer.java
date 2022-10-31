package nl.tomkemper.bep3.messagingpatterns.producer.requestreply;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
public class RequestReplyProducer {

    private RabbitTemplate rabbit;
    private static final ParameterizedTypeReference<CounterReply> replyType = new ParameterizedTypeReference<>() {
    };

    public RequestReplyProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void incrementCounter(String key) {
        this.rabbit.convertAndSend("incrementcounter-example", IncrementCommand.of(key));
    }

    public int getCounter(String key) {
        CounterReply reply = this.rabbit.convertSendAndReceiveAsType("querycounter-example", CounterQuery.of(key), replyType);
        return reply.count;
    }

}
