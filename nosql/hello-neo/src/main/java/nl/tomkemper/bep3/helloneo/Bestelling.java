package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Node
public class Bestelling {
    @Id
    @GeneratedValue //LET OP: dit is niet de jakarta-persistence @GeneratedId
    private Long bestellingId;

    @Relationship(type="ORDERS", direction = Relationship.Direction.INCOMING)
    private Klant klant;
    private LocalDate datum = LocalDate.now();

    @Relationship(type="CONTAINS", direction = Relationship.Direction.OUTGOING)
    private List<BesteldArtikel> artikelen = new ArrayList<>();

    public List<BesteldArtikel> getArtikelen() {
        return artikelen;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Bestelling(Klant k){
        this.klant = k;
    }

    public Bestelling add(int stuks, Artikel artikel){
        this.getArtikelen().add(new BesteldArtikel(artikel, stuks));
        return this;
    }

    public Bestelling add(Artikel artikel){
        this.getArtikelen().add(new BesteldArtikel(artikel, 1));
        return this;
    }
}
