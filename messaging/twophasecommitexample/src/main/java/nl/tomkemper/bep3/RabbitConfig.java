package nl.tomkemper.bep3;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate toUse = new RabbitTemplate(factory);
        toUse.setMessageConverter(getConverter());
        toUse.setChannelTransacted(true);
        return toUse;
    }

    @Bean
    public MessageConverter getConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue getQueue() {
        return QueueBuilder.durable("twophasemessages").build();
    }
}
