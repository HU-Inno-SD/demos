package nl.tomkemper.bep3.hellomongo.fabriek2;

import java.util.ArrayList;
import java.util.List;

public class Klant {

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
