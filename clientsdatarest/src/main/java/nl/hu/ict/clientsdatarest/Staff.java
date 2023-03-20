package nl.hu.ict.clientsdatarest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Staff {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    protected Staff(){}

    public Staff(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
