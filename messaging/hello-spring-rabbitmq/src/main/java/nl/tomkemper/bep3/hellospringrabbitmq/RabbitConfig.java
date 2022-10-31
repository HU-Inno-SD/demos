package nl.tomkemper.bep3.hellospringrabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue demoQueue(){
        return QueueBuilder.durable("demo-queue").build();
    }

    @Bean
    MessageConverter getConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
