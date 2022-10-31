package nl.tomkemper.bep3.messagingpatterns.consumer.fireforget;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FireForgetListener {

    @RabbitListener(queues = {"fireforget-example"})
    public void listenToRegisters(TralalaTotaalIetsAnders mailCommand) {
        System.out.printf("Mailcommando %s: %s kwam binnen, we gaan er vanuit dat deze listener meningen heeft over wat voor mail...%n", mailCommand.getId(), mailCommand.getEmail());
    }
}
