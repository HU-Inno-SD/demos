package nl.tomkemper.bep3.whutsupp.startup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import nl.tomkemper.bep3.whutsupp.ChatMessage;
import nl.tomkemper.bep3.whutsupp.KlasRepository;
import nl.tomkemper.bep3.whutsupp.Whutsupp;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import static nl.tomkemper.bep3.whutsupp.Whutsupp.*;
import java.nio.charset.StandardCharsets;

@Component
@Order(2)
public class RabbitInit implements CommandLineRunner {

    private final RabbitAdmin admin;
    private final RabbitTemplate template;
    private final KlasRepository klassen;

    public RabbitInit(
            RabbitAdmin admin, RabbitTemplate template, KlasRepository klassen) {
        this.admin = admin;
        this.template = template;
        this.klassen = klassen;
    }

    @Override
    public void run(String... args) throws Exception {


        if (Whutsupp.TEST_DATA) {
            this.template.convertAndSend(INCOMING_QUEUE, new ChatMessage("Hello World"));

            //this.template.convertAndSend(ANNOUNCE_EXCHANGE, "asdf", new ChatMessage("Hallo allemaal"));
        }
    }
}
