package nl.tomkemper.bep3.messagingpatterns.consumer.fireforget;

import java.util.UUID;

public class TralalaTotaalIetsAnders {
    private UUID id;
    private String email;

    protected TralalaTotaalIetsAnders() {
    }

    public TralalaTotaalIetsAnders(UUID id, String email) {

        this.id = id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public UUID getId() {
        return id;
    }
}
