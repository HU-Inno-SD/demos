package nl.tomkemper.bep3.hellospringrabbitmq;

import java.util.UUID;

public class SomeMessage {
    private UUID uuid;
    private String content;

    protected SomeMessage(){}

    public SomeMessage(String message){
        this.uuid = UUID.randomUUID();
        this.content = message;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getContent() {
        return content;
    }
}
