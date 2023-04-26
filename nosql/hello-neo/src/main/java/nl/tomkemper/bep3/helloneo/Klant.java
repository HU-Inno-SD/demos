package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Klant {

    @Id
    @GeneratedValue
    private long klantId;
    private String name;

    public long getKlantId() {
        return klantId;
    }

    public String getName() {
        return name;
    }

    public Klant(String name){

        this.name = name;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "klantId=" + klantId +
                ", name='" + name + '\'' +
                '}';
    }
}
