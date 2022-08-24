package nl.hu.sd.inno.basicboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Counter {

    @Id
    @GeneratedValue
    private Long id;

    private int currentValue = 0;

    public Counter(){

    }

    public Long getId() {
        return id;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void increment() {
        this.currentValue++;
    }
}
