package nl.hu.sd.inno.badaggregate.exhibitions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class TradeFair {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDateTime start;

    public Long getId() {
        return id;
    }

    protected TradeFair(){

    }

    public TradeFair(String name, LocalDateTime start) {
        this.name = name;
        this.start = start;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void postpone(LocalDateTime newDateTime){
        this.start = newDateTime;
    }
}
