package nl.tomkemper.bep3.helloneo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
@Component
@EnableNeo4jRepositories
public class HelloNeo implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(HelloNeo.class, args);


    }

    private final Neo4jClient neoClient;
    private final Neo4jTemplate neoTemplate;

    private final KlantRepository klanten;

    public HelloNeo(Neo4jClient neoClient, Neo4jTemplate neoTemplate, KlantRepository klanten) {
        this.neoClient = neoClient;
        this.neoTemplate = neoTemplate;
        this.klanten = klanten;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.neoClient.query("MATCH (n) DETACH DELETE n").run();

        Klant smit = new Klant("Smit");
        Klant staal = new Klant( "Staal");

        klanten.save(smit);
        klanten.save(staal);

        System.out.println(smit);
        System.out.println(staal);

    }
}
