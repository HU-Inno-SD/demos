package immutable;

public class CounterValue {

    private int value;

    public CounterValue(int value) {
        this.value = value;
    }


    public int getCount() {
        return value;
    }

    public CounterValue increment(){
        return new CounterValue(this.value + 1);
    }

    public CounterValue clone(){
        return new CounterValue(this.value);
    }

    public CounterValue add(CounterValue other){
        return new CounterValue(this.getCount() + other.getCount());
    }

    @Override
    public String toString() {
        return "CounterValue{" +
                "value=" + value +
                '}';
    }
}
