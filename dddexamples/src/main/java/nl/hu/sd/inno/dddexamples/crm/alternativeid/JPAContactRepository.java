package nl.hu.sd.inno.dddexamples.crm.alternativeid;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class JPAContactRepository implements AlternativeContactRepository{

    private final EntityManager entities;

    public JPAContactRepository(EntityManager entities){
        this.entities = entities;
    }

    @Override
    public Optional<Contact> findById(ContactId id) {
        Contact c = this.entities.find(Contact.class, id.value());
        if(c == null){
            return Optional.empty();
        }else{
            return Optional.of(c);
        } //Er is een speciale plek in de hel voor mensen die Optional.of(null) doen.
    }

    @Override
    public List<Contact> findAll() {
        return this.entities.createQuery("select c from ContactAltId c", Contact.class).getResultList();
    }

    @Override
    public void persist(Contact c) {
        this.entities.persist(c);
    }
}
