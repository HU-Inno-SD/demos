package nl.hu.ict.inno.hellomongo.fabriek1;

public class Artikel {
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
