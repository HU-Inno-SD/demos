package nl.hu.ict.inno.hellomongo.fabriek1;

import java.util.ArrayList;
import java.util.List;

public class Klant {

    private long id;
    private String name;
    private List<Bestelling> bestellingen = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Bestelling> getBestellingen() {
        return bestellingen;
    }

    public Klant(long id, String name){
        this.id = id;
        this.name = name;
    }
}
