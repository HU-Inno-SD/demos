package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Klant {

    @Id
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Klant(long id, String name){
        this.id = id;
        this.name = name;
    }
}
