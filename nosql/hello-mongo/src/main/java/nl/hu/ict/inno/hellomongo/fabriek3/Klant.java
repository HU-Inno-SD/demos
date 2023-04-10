package nl.hu.ict.inno.hellomongo.fabriek3;

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
