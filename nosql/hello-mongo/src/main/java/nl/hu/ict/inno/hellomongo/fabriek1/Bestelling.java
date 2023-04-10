package nl.hu.ict.inno.hellomongo.fabriek1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bestelling {

    private LocalDate datum = LocalDate.now();
    private List<BesteldArtikel> artikelen = new ArrayList<>();

    public List<BesteldArtikel> getArtikelen() {
        return artikelen;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Bestelling add(int stuks, Artikel artikel){
        this.getArtikelen().add(new BesteldArtikel(artikel, stuks));
        return this;
    }

    public Bestelling add(Artikel artikel){
        this.getArtikelen().add(new BesteldArtikel(artikel, 1));
        return this;
    }

    public static class BesteldArtikel {
        private Artikel artikel;
        private int aantal;
        private double prijs;

        public BesteldArtikel(Artikel artikel, int aantal){
            this.aantal = aantal;
            this.artikel = artikel;
            this.prijs = artikel.getAdviesPrijs();
        }

        public Artikel getArtikel() {
            return artikel;
        }

        public int getAantal() {
            return aantal;
        }

        public double getPrijs() {
            return prijs;
        }
    }
}
