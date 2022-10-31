package nl.tomkemper.bep3.messagingpatterns.consumer.inheritance;

public class EmailChangedEvent extends Event {
    public String newEmail;

    @Override
    public void print() {
        System.out.printf("Event %s verandert mijn email naar %s%n", eventId, newEmail);
    }
}
