package nl.tomkemper.bep3;

public class MessageContract {
    public String getValue() {
        return value;
    }

    private String value;

    public MessageContract() { /*voor Jackson-JSON*/ }

    public MessageContract(String value) {
        this.value = value;
    }
}
