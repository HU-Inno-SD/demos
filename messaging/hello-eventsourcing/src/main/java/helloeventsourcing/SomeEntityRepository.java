package helloeventsourcing;

import helloeventsourcing.events.EntityCreated;
import helloeventsourcing.events.SomeEntityEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;


@Transactional
@Component
public class SomeEntityRepository {

    private EntityManager entities;

    public SomeEntityRepository(EntityManager entities) {
        this.entities = entities;
    }

    public SomeEntity get(UUID id) {
        List<SomeEntityEvent> events = this.entities
                .createQuery("Select e from SomeEntityEvent e where e.entityId = :eid order by e.id")
                .setParameter("eid", id)
                .getResultList();

        return events.stream().reduce(null, (r, e) -> e.applyTo(r), (r1, r2) -> r1);
    }

    public void save(SomeEntity entity) {
        for (SomeEntityEvent e : entity.getEvents()) {
            this.entities.persist(e);
        }
    }
}
