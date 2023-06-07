package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class Klant {

    @Id
    @GeneratedValue //LET OP: dit is niet de jakarta-persistence @GeneratedId
    private long klantId;
    private String name;

    public Adres getAdres() {
        return new Adres(this.straat, this.huisnr);
    }

    public void verhuis(Adres nieuwAdres){
        this.straat = nieuwAdres.getStraat();
        this.huisnr = nieuwAdres.getHuisnr();
    }
    /*

    Dit zouden we graag doen in Neo4J, maar de library ondersteund dat niet.
    Maar dat is niet zo erg, omdat we met het algemene concept Value Objects dit relatief makkelijk zelf kunnen bouwen.

    Het ziet er niet zo fraai uit, maar het is *private* niet-zo-fraai. En private niet-zo-fraai is niet-zo-erg.
    @Embedded
    private Adres adres;
 */

    @Property("address.straat")
    private String straat;
    @Property("address.huisnr")
    private int huisnr;

    public long getKlantId() {
        return klantId;
    }

    public String getName() {
        return name;
    }

    public Klant(String name, Adres adres) {

        this.name = name;
        this.straat = adres.getStraat();
        this.huisnr = adres.getHuisnr();
    }

    @Override
    public String toString() {
        return "Klant{" +
                "klantId=" + klantId +
                ", name='" + name + '\'' +
                ", adres=" + getAdres() +
                '}';
    }
}
