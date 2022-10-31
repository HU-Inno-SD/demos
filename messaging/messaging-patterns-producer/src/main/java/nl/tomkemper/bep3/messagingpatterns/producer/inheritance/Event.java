package nl.tomkemper.bep3.messagingpatterns.producer.inheritance;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

//zorgt er voor dat er extra info wordt meegestuurd om inheritance aan de andere kant uit te kunnen pakken
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
public class Event {
    public UUID eventId = UUID.randomUUID();
}
