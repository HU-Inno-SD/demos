package nl.tomkemper.bep3.helloneo;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Artikel {
    @Id
    private long id;
    private String name;
    private double adviesPrijs;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAdviesPrijs() {
        return adviesPrijs;
    }

    public Artikel(long id, String name, double adviesPrijs) {
        this.id = id;
        this.name = name;
        this.adviesPrijs = adviesPrijs;
    }
}
