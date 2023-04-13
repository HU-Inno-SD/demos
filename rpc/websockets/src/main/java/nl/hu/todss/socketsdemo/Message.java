package nl.hu.todss.socketsdemo;

public class Message {
    private String sender;
    private String content;

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }
}
