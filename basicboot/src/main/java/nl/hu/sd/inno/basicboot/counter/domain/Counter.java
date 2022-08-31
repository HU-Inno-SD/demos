package nl.hu.sd.inno.basicboot.counter.domain;

public class Counter {

    private int currentValue = 0;

    private Counter() {

    }

    private static final Counter instance = new Counter();

    public static Counter getInstance() {
        return instance;
    }

    public void reset() {
        this.currentValue = 0;
    }


    public int getCurrentValue() {
        return currentValue;
    }

    public void increment() {
        this.currentValue++;
    }
}
