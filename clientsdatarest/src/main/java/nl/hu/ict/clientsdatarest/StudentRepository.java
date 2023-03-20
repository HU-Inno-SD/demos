package nl.hu.ict.clientsdatarest;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface StudentRepository extends JpaRepository<Student, Long> {
}
