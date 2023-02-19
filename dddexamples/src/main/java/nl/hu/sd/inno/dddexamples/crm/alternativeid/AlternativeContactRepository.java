package nl.hu.sd.inno.dddexamples.crm.alternativeid;

import java.util.List;
import java.util.Optional;

public interface AlternativeContactRepository {

    Optional<Contact> findById(ContactId id);
    List<Contact> findAll();

    void persist(Contact c);

}
