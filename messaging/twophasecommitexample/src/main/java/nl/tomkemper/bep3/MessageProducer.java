package nl.tomkemper.bep3;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Component
@Transactional
public class MessageProducer {

    @Autowired
    private EntityManager entities;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String value) {
        MessageEntity newMessage = new MessageEntity(value);

        System.out.println("Persisting Send");
        entities.persist(newMessage);

        System.out.println("Sending");
        rabbitTemplate.convertAndSend("twophasemessages", new MessageContract(
                String.format("Sending message %s: %s", newMessage.getId(), newMessage.getContent())));

        System.out.println("Sent");
        throw new RuntimeException("Error on Sending");
    }
}
