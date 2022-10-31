package nl.tomkemper.bep3.messagingpatterns.consumer.competingconsumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CompetingConsumer2 {

    @RabbitListener(queues = {"competingconsumers-example"})
    public void processImageSlowly(ProcessImageCommand cmd) throws InterruptedException {
        System.out.printf("Consumer 2 is heel langzaam image %s aan het processen%n", cmd.imageId);
        Thread.sleep(5000);
        System.out.printf("Consumer 2 is eindelijk klaar met %s%n", cmd.imageId);
    }
}
