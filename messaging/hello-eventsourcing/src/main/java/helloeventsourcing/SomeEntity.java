package helloeventsourcing;

import helloeventsourcing.events.*;

import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;

public class SomeEntity implements SomeEntityEventHandler {
    private UUID id = UUID.randomUUID();
    private Queue<SomeEntityEvent> events = new LinkedBlockingQueue<SomeEntityEvent>();

    public Iterable<SomeEntityEvent> getEvents() {
        return events;
    }

    private String name;
    private String email;
    private String phonenr;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenr() {
        return phonenr;
    }

    public SomeEntity(String name, String email) {
        this(name, email, null);
    }

    public SomeEntity(String name, String email, String phonenr) {
        this.name = name;
        this.email = email;
        this.phonenr = phonenr;
        this.events.add(new EntityCreated(id, name, email, phonenr));
    }

    public void changeContactDetails(String newEmail, String newPhone) {
        ContactDataChanged event = new ContactDataChanged(this.id, newEmail, newPhone);
        this.events.add(event);
        this.apply(event);
    }

    public void changeName(String newName) {
        NameTypoFixed event = new NameTypoFixed(this.id, newName);
        this.events.add(event);
        this.apply(event);
    }

    public SomeEntity apply(NameTypoFixed event) {
        this.name = event.name;
        return this;
    }

    public SomeEntity apply(ContactDataChanged event) {
        if (event.email != null) {
            this.email = event.email;
        }
        if (event.phonenr != null) {
            this.phonenr = event.phonenr;
        }
        return this;
    }

    public static SomeEntity fromCreatedEvent(EntityCreated event) {
        SomeEntity result = new SomeEntity(event.name, event.email, event.phonenr);
        result.id = event.entityId;
        result.events.clear();
        return result;
    }

}
