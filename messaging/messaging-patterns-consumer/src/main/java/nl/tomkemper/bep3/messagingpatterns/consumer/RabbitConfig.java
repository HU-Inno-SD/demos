package nl.tomkemper.bep3.messagingpatterns.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue fireAndForgetQueue() {
        return QueueBuilder.durable("fireforget-example").build();
    }

    @Bean
    public Queue requestCounterQueue() {
        return QueueBuilder.durable("querycounter-example").build();
    }

    @Bean
    public Queue incrementCounterQueue() {
        return QueueBuilder.durable("incrementcounter-example").build();
    }


    @Bean
    public DirectExchange pubsubExchange(){
        return ExchangeBuilder.directExchange("pubsubdemo").build();
    }

    @Bean
    public Queue pubsubQueue1() {
        return QueueBuilder.durable("happynewyear1-example").build();
    }

    @Bean
    public Queue pubsubQueue2() {
        return QueueBuilder.durable("happynewyear2-example").build();
    }

    @Bean
    public Binding bindQueue1() {
        return BindingBuilder.bind(pubsubQueue1()).to(pubsubExchange()).with("happy-new-year");
    }

    @Bean
    public Binding bindQueue2() {
        return BindingBuilder.bind(pubsubQueue2()).to(pubsubExchange()).with("happy-new-year");
    }

    @Bean
    public Queue competingExample() {
        return QueueBuilder.durable("competingconsumers-example").build();
    }

    @Bean
    public Queue inheritanceExample() {
        return QueueBuilder.durable("inheritance-example").build();
    }

    @Bean
    public MessageConverter converter() {

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        return converter;
    }
}
