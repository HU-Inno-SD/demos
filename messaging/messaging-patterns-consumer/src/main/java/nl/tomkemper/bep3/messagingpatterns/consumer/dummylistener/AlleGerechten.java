package nl.tomkemper.bep3.messagingpatterns.consumer.dummylistener;


import java.util.List;
import java.util.UUID;

public class AlleGerechten {


    public AlleGerechten() {
    }


    public String getEventKey() {
        return "keywords.gerecht.gerecht";
    }


    public UUID getEventId() {
        return this.eventId;
    }


    private final UUID eventId = UUID.randomUUID();


}
