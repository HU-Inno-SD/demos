package nl.tomkemper.bep3.messagingpatterns.consumer.inheritance;

public class FingersChoppedOffEvent extends Event {
    public int nrOfFingersLost;

    @Override
    public void print() {
        System.out.printf("Ik ben event %s, en ik heb pijn, want ik ben %d vingers kwijt %n", eventId, nrOfFingersLost);
    }
}
