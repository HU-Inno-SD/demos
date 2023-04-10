package nl.hu.ict.inno.hellomongo.fabriek2;

import java.util.UUID;

public class BesteldArtikel {
    private String id = UUID.randomUUID().toString();

    private long artikelId;
    private String bestellingId;
    private int aantal;
    private double prijs;

    public BesteldArtikel(Bestelling bestelling, Artikel artikel, int aantal) {
        this.aantal = aantal;
        this.artikelId = artikel.getId();
        this.bestellingId = bestelling.getId();
        this.prijs = artikel.getAdviesPrijs();
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
