package nl.tomkemper.bep3.whutsupp;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KlasRepository extends CrudRepository<Klas, Long> {

    @Query(value = "{ 'students._id' : ?0 }")
    Optional<Klas> findKlasByStudent(long studentnr);
}
