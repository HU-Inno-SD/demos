package nl.hu.ict.inno.hellomongo.fabriek1;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Fabriek1Main {

    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb://localhost");
        MongoOperations mongoOps = new MongoTemplate(client, "fabriek1");

        mongoOps.dropCollection("klant");

        Klant smit = new Klant(121, "Smit");
        Klant staal = new Klant(122, "Staal");

        Artikel postits = new Artikel(121, "post-its", 2.75);
        Artikel pennen = new Artikel(122, "high light pen", 1.50);
        Artikel diskettes = new Artikel(123, "diskettes 10pk", 3.10);
        Artikel nietmachine = new Artikel(124, "nietmachine", 4.75);

        mongoOps.save(smit);
        mongoOps.save(staal);

        smit.getBestellingen().add(
                new Bestelling()
                        .add(10, postits)
                        .add(3, diskettes)
                        .add(nietmachine));

        mongoOps.save(smit);

        System.out.println(mongoOps.findAll(Klant.class));


    }
}
