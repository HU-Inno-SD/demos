package nl.hu.ict.inno.hellomongo.fabriek3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Bestelling {
    private String id = UUID.randomUUID().toString();

    private long klantId;
    private LocalDate datum = LocalDate.now();
    private List<BesteldArtikel> artikelen = new ArrayList<>();

    public List<BesteldArtikel> getArtikelen() {
        return artikelen;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Bestelling(Klant k){
        this.klantId = k.getId();
    }

    public Bestelling add(int stuks, Artikel artikel){
        this.getArtikelen().add(new BesteldArtikel(artikel, stuks));
        return this;
    }

    public Bestelling add(Artikel artikel){
        this.getArtikelen().add(new BesteldArtikel(artikel, 1));
        return this;
    }

    public long getKlantId() {
        return klantId;
    }

    public String getId() {
        return id;
    }

    public static class BesteldArtikel {
        private long artikelId;
        private int aantal;
        private double prijs;
        private String naam;

        public BesteldArtikel(Artikel artikel, int aantal){
            this.aantal = aantal;
            this.artikelId = artikel.getId();
            this.prijs = artikel.getAdviesPrijs();
            this.naam = artikel.getName();
        }

        public long getArtikelId() {
            return artikelId;
        }

        public int getAantal() {
            return aantal;
        }

        public double getPrijs() {
            return prijs;
        }
    }
}
