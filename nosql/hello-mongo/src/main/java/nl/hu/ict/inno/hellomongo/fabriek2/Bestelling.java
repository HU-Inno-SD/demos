package nl.hu.ict.inno.hellomongo.fabriek2;

import java.time.LocalDate;
import java.util.UUID;

public class Bestelling {
    private String id = UUID.randomUUID().toString();
    private long klantId;
    private LocalDate datum = LocalDate.now();
    public LocalDate getDatum() {
        return datum;
    }

    public Bestelling(Klant k){
        this.klantId = k.getId();
    }

    public BesteldArtikel add(int stuks, Artikel artikel){
        return new BesteldArtikel(this, artikel, stuks);

    }

    public BesteldArtikel add(Artikel artikel){
        return new BesteldArtikel(this, artikel, 1);
    }


    public String getId() {
        return this.id;
    }
}
