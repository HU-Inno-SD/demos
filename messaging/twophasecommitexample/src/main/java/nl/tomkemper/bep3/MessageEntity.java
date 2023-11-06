package nl.tomkemper.bep3;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class MessageEntity {
    @Id
    @GeneratedValue
    private Long id;

    public String content;

    @Version
    private int version;

    protected MessageEntity() {/*voor Hibernate*/}

    public MessageEntity(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getVersion() {
        return version;
    }
}
