package nl.tomkemper.bep3.helloneo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    private final BestellingRepository bestellingen;

    public HelloNeo(Neo4jClient neoClient, Neo4jTemplate neoTemplate, KlantRepository klanten, BestellingRepository bestellingen) {
        this.neoClient = neoClient;
        this.neoTemplate = neoTemplate;
        this.klanten = klanten;
        this.bestellingen = bestellingen;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        //Smijt alles weg
        this.neoClient.query("MATCH (n) DETACH DELETE n").run();

        Klant smit = new Klant("Smit", new Adres("Andesdreef", 42));
        Klant staal = new Klant("Staal", new Adres("Roelanddreef", 99));
        Artikel postits = new Artikel(121, "Post-its", 2.75);
        Artikel pen = new Artikel(121, "High light pen", 1.50);
        Artikel diskettes = new Artikel(121, "Diskettes 10pk", 3.10);

        for (Object o : List.of(smit, staal, postits, pen, diskettes)) {
            this.neoTemplate.save(o);
        }

        Bestelling b1 = new Bestelling(smit);
        b1.add(10, postits);
        b1.add(5, pen);

        //Persoonlijk vind ik NeoTemplate fijner, maar ok. Repositories zoals bij BEP2 kunnen ook.
        this.bestellingen.save(b1);

        System.out.println(smit);
        System.out.println(staal);

    }
}
