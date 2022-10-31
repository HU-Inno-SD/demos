package helloeventsourcing.events;

import helloeventsourcing.SomeEntity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "SomeEntityEvents")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class SomeEntityEvent {
    @Id
    @GeneratedValue
    protected Long id;
    public UUID entityId;

    protected SomeEntityEvent(){
        //For Hibernate and beyond!
    }

    public SomeEntityEvent(UUID entityId){
        this.entityId = entityId;
    }

    public abstract SomeEntity applyTo(SomeEntityEventHandler handler);
}
