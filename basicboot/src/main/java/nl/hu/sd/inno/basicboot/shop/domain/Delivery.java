package nl.hu.sd.inno.basicboot.shop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate deliveryDate;

    private int nrDelivered;

    @ManyToOne
    private Product product;

    public Long getId() {
        return id;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public int getNrDelivered() {
        return nrDelivered;
    }

    public Product getProduct() {
        return product;
    }

    protected Delivery(){}

    public Delivery(Product product, int nrDelivered) {
        this.nrDelivered = nrDelivered;
        this.product = product;
        this.deliveryDate = LocalDate.now();
    }

    public void process() {
        this.product.deliver(this.nrDelivered);
    }
}
