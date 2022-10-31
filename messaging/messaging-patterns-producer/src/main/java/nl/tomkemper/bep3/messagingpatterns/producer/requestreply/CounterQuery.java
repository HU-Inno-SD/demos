package nl.tomkemper.bep3.messagingpatterns.producer.requestreply;

public class CounterQuery {
    //we gebruiken deze class puur als DTO, dus public fields zijn wmbt. acceptabel
    public String key;


    public static CounterQuery of(String key){
        CounterQuery cmd = new CounterQuery();
        cmd.key = key;
        return cmd;
    }
}
