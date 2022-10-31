package nl.tomkemper.bep3.messagingpatterns.producer.competingconsumers;

import java.util.UUID;

public class ProcessImageCommand {
    public UUID imageId;

    public static ProcessImageCommand random(){
        ProcessImageCommand cmd = new ProcessImageCommand();
        cmd.imageId = UUID.randomUUID();
        return cmd;
    }
}
