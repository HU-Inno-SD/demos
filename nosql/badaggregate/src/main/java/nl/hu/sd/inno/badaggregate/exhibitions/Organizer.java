package nl.hu.sd.inno.badaggregate.exhibitions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Organizer {
    @Id
    @GeneratedValue
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    protected Organizer(){}

    public Organizer(String name) {
        this.name = name;
    }
}
