package nl.tomkemper.bep3;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;

@Component
@Transactional
public class MessageListener {

    @Autowired
    private EntityManager entities;

    @RabbitListener(queues = {"twophasemessages"})
    public void receiveMessage(MessageContract m) {
        System.out.println("Received " + m.getValue());
        MessageEntity message = new MessageEntity("Received " + m.getValue());
        entities.persist(message);
        System.out.println("Persisted received");
//        throw new RuntimeException("Error on Receive!");
    }

//    @RabbitListener(queues = {"twophasemessages"}, ackMode = "MANUAL")
//    public void receiveMessage(
//            MessageContract m,
//            Channel channel,
//            @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        System.out.println("Received " + m.getValue());
//        MessageEntity message = new MessageEntity("Received " + m.getValue());
//        entities.persist(message);
//        System.out.println("Persisted received");
//        channel.basicAck(tag, false);
////        throw new RuntimeException("Error on Receive!");
//    }
}
