package helloeventsourcing.events;

import helloeventsourcing.SomeEntity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@DiscriminatorValue("EntityCreated")
public class EntityCreated extends SomeEntityEvent {

    public String name;
    public String email;
    public String phonenr;

    protected EntityCreated(){
        //for Hibernate & Friends
    }

    public EntityCreated(UUID entityId, String name, String email, String phonenr) {
        this.entityId = entityId;
        this.name = name;
        this.email = email;
        this.phonenr = phonenr;
    }

    public Long getId(){
        return this.id;
    }

    @Override
    public SomeEntity applyTo(SomeEntityEventHandler handler) {
        return SomeEntity.fromCreatedEvent(this);
    }
}
