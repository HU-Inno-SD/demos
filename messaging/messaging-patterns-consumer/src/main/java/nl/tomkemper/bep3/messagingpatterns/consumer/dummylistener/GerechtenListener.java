package nl.tomkemper.bep3.messagingpatterns.consumer.dummylistener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class GerechtenListener {


    @RabbitListener(queues = {"gerecht-keywords"})
    public void wut(AlleGerechten g){
        System.out.println("EEEEEEINDELIJK");
        System.out.println(g.getEventId());
    }
}
