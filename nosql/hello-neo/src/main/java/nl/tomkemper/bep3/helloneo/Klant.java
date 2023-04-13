package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Klant {

    @Id
    private long klantId;
    private String name;

    public long getKlantId() {
        return klantId;
    }

    public String getName() {
        return name;
    }

    public Klant(long id, String name){
        this.klantId = id;
        this.name = name;
    }
}
