package nl.tomkemper.bep3.messagingpatterns.consumer.inheritance;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailChangedEvent.class),
        @JsonSubTypes.Type(value = FingersChoppedOffEvent.class)
})
public class Event {
    public UUID eventId;

    public void print() {
        System.out.printf("Ik ben base event %s, dat is niet goed, want in het echt ben ik abstract %n", eventId);
    }
}
