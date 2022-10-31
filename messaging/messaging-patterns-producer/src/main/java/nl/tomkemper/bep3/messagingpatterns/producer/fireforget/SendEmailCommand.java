package nl.tomkemper.bep3.messagingpatterns.producer.fireforget;

import java.util.UUID;

public class SendEmailCommand {
    private UUID id;
    private String email;

    protected SendEmailCommand() {
    }

    public SendEmailCommand(UUID id, String email) {

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
