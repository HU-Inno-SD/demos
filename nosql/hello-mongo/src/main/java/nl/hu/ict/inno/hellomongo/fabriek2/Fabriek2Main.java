package nl.hu.ict.inno.hellomongo.fabriek2;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Fabriek2Main {

    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb://localhost");
        MongoOperations mongoOps = new MongoTemplate(client, "fabriek2");

        mongoOps.dropCollection("klant");
        mongoOps.dropCollection("artikel");
        mongoOps.dropCollection("bestelling");
        mongoOps.dropCollection("besteldArtikel");

        Klant smit = new Klant(121, "Smit");
        Klant staal = new Klant(122, "Staal");

        Artikel postits = new Artikel(121, "post-its", 2.75);
        Artikel pennen = new Artikel(122, "high light pen", 1.50);
        Artikel diskettes = new Artikel(123, "diskettes 10pk", 3.10);
        Artikel nietmachine = new Artikel(124, "nietmachine", 4.75);

        for (Klant k : new Klant[]{smit, staal}) {
            mongoOps.save(k);
        }

        for (Artikel a : new Artikel[]{postits, pennen, diskettes, nietmachine}) {
            mongoOps.save(a);
        }

        Bestelling b1 = new Bestelling(smit);
        mongoOps.save(b1);

        for (BesteldArtikel ba : new BesteldArtikel[]{
                b1.add(10, postits),
                b1.add(3, diskettes),
                b1.add(nietmachine)}) {
            mongoOps.save(ba);
        }

    }
}
