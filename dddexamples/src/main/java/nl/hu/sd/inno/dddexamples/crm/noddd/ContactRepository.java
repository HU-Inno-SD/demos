package nl.hu.sd.inno.dddexamples.crm.noddd;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
