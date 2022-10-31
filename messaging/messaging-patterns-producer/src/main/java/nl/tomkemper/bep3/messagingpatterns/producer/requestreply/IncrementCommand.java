package nl.tomkemper.bep3.messagingpatterns.producer.requestreply;

public class IncrementCommand {
    public String key;

    public static IncrementCommand of(String key){
        IncrementCommand cmd = new IncrementCommand();
        cmd.key = key;
        return cmd;
    }
}
