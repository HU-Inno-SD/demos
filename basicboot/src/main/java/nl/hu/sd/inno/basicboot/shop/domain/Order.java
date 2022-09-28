package nl.hu.sd.inno.basicboot.shop.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Person person;

    private LocalDate orderDate;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="id.order")
    private List<OrderLine> orderLines = new ArrayList<>();

    protected Order(){}

    public Order(Person person){
        Objects.requireNonNull(person);
        this.person = person;
        this.orderDate = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public List<OrderLine> getOrderLines() {
        return Collections.unmodifiableList(orderLines);
    }

    public void addProduct(Product p, int nr){
        this.orderLines.add(new OrderLine(this, p, nr));
    }

    public LocalDate getDate() {
        return orderDate;
    }

    public void process() {
        for(OrderLine line: this.orderLines){
            line.getProduct().order(line.nr);
        }
    }


    @Entity
    @Table(name = "orderlines")
    public static class OrderLine {
        @EmbeddedId
        private OrderLineId id;

        protected OrderLine(){}

        public OrderLine(Order order, Product p, int nr) {
            Objects.requireNonNull(order);
            Objects.requireNonNull(p);

            this.id = new OrderLineId();
            this.id.order = order;
            this.id.product = p;
            this.nr = nr;
        }

        public int getNr() {
            return nr;
        }

        public Product getProduct(){
            return id.product;
        }

        private int nr;

        @Embeddable
        private static class OrderLineId implements Serializable {
            @ManyToOne
            private Order order;
            @ManyToOne
            private Product product;
        }

    }
}
