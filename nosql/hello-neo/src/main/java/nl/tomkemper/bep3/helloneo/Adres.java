package nl.tomkemper.bep3.helloneo;

import java.util.Objects;

//@Embeddable Dit is helaas geen ding in Neo4J
public class Adres {
    private String straat;
    private int huisnr;
    public Adres(String straat, int huisnr) {
        Objects.requireNonNull(straat);
        this.straat = straat;
        this.huisnr = huisnr;
    }

    public String getStraat() {
        return straat;
    }

    public int getHuisnr() {
        return huisnr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adres adres = (Adres) o;
        return huisnr == adres.huisnr && straat.equals(adres.straat);
    }

    @Override
    public String toString() {
        return "Adres{" +
                "straat='" + straat + '\'' +
                ", huisnr=" + huisnr +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(straat, huisnr);
    }
}
