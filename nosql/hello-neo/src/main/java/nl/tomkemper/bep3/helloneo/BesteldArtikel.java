package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

import java.lang.annotation.Target;

@RelationshipProperties
public class BesteldArtikel {
    @RelationshipId
    private Long id;
    @TargetNode
    private Artikel artikel;
    private int aantal;
    private double prijs;

    public BesteldArtikel(Artikel artikel, int aantal){
        this.aantal = aantal;
        this.prijs = artikel.getAdviesPrijs();
        this.artikel = artikel;
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
