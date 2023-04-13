package nl.tomkemper.bep3.helloneo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class HelloNeo implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(HelloNeo.class, args);


    }

    private final Neo4jClient neoClient;
    private final Neo4jTemplate neoTemplate;

    public HelloNeo(Neo4jClient neoClient, Neo4jTemplate neoTemplate) {
        this.neoClient = neoClient;
        this.neoTemplate = neoTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        this.neoClient.query("MATCH (n) DETACH DELETE n").run();

        Klant smit = new Klant(121, "Smit");
        Klant staal = new Klant(122, "Staal");

        this.neoTemplate.save(smit);
        this.neoTemplate.save(staal);

        Artikel postits = new Artikel(121, "post-its", 2.75);
        Artikel pennen = new Artikel(122, "high light pen", 1.50);
        Artikel diskettes = new Artikel(123, "diskettes 10pk", 3.10);
        Artikel nietmachine = new Artikel(124, "nietmachine", 4.75);

        for (Artikel a : new Artikel[]{postits, pennen, diskettes, nietmachine}) {
            this.neoTemplate.save(a);
        }

        Bestelling b = new Bestelling(smit).add(100, postits).add(2, pennen);

        this.neoTemplate.save(b);

        //MATCH (k:Klant)-->(b:Bestelling)-[i:ITEM]->(m) RETURN k.name,b.datum,sum(i.aantal * i.prijs)

    }
}
