package nl.tomkemper.bep3.messagingpatterns.producer.inheritance;

public class FingersChoppedOffEvent extends Event {
    public int nrOfFingersLost;

    public static FingersChoppedOffEvent of(int i) {
        FingersChoppedOffEvent e = new FingersChoppedOffEvent();
        e.nrOfFingersLost = i;
        return e;
    }
}
