package nl.hu.sd.inno.basicboot.domain;

public class Counter {

    private int currentValue = 0;

    private Counter() {

    }

    private static final Counter instance = new Counter();

    public static Counter getInstance() {
        return instance;
    }


    public int getCurrentValue() {
        return currentValue;
    }

    public void increment() {
        this.currentValue++;
    }
}
