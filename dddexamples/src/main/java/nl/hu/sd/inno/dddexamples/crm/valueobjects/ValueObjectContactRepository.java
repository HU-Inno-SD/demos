package nl.hu.sd.inno.dddexamples.crm.valueobjects;
import org.springframework.data.jpa.repository.JpaRepository;

//Hmm, repository interfaces moeten uniek zijn by name, dat is onhandig
public interface ValueObjectContactRepository extends JpaRepository<Contact, ContactId> {
}
