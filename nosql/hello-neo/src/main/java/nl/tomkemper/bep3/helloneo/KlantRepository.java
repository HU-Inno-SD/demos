package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface KlantRepository extends Neo4jRepository<Klant, Long> {
}
