package nl.hu.ict.clientsdatarest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "staff", path = "staff")
public interface StaffRepository extends JpaRepository<Staff, Long> {
}
