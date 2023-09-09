package nl.hu.sd.inno.badaggregate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Registration {

    @Id
    private String id;

    private int lastPage;

    protected Registration(){}

    public Registration(String id) {
        this.id = id;
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
