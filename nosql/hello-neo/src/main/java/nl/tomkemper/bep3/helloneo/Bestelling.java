package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Node
public class Bestelling {
    @Id
    private String id = UUID.randomUUID().toString();

    @Relationship(type = "KOPER", direction = Relationship.Direction.INCOMING)
    private Klant klant;
    private LocalDate datum = LocalDate.now();
    @Relationship(type = "ITEM", direction = Relationship.Direction.OUTGOING)
    private List<BesteldArtikel> artikelen = new ArrayList<>();

    public List<BesteldArtikel> getArtikelen() {
        return artikelen;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Bestelling(Klant k) {
        this.klant = k;
    }

    public Bestelling add(int stuks, Artikel artikel) {
        this.getArtikelen().add(new BesteldArtikel(artikel, stuks));
        return this;
    }

    public Bestelling add(Artikel artikel) {
        this.getArtikelen().add(new BesteldArtikel(artikel, 1));
        return this;
    }

    public Klant getKlant() {
        return klant;
    }

    public String getId() {
        return id;
    }


    @RelationshipProperties
    public static class BesteldArtikel {
        @Id
        @GeneratedValue
        private Long id;

        @TargetNode
        private Artikel artikel;
        private int aantal;
        private double prijs;

        public BesteldArtikel(Artikel artikel, int aantal) {
            this.aantal = aantal;
            this.artikel = artikel;
            this.prijs = artikel.getAdviesPrijs();
        }

        public Artikel getArtikel() {
            return artikel;
        }

        public int getAantal() {
            return aantal;
        }

        public double getPrijs() {
            return prijs;
        }
    }
}
