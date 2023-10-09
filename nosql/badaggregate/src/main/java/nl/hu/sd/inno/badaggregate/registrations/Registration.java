package nl.hu.sd.inno.badaggregate.registrations;

import nl.hu.sd.inno.badaggregate.exhibitions.TradeFair;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Registration {

    @Id
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private TradeFair event;

    public TradeFair getEvent(){ //Dit is foute boel!
        return this.event;
    }

    private int lastPage;

    protected Registration(){}

    public Registration(String id, TradeFair tradeFair) {

        this.id = id;
        this.event = tradeFair;
    }


    public String getId() {
        return id;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }
}
