package nl.hu.sd.inno.basicboot.shop.application;

import nl.hu.sd.inno.basicboot.shop.data.OrderRepository;
import nl.hu.sd.inno.basicboot.shop.data.PersonRepository;
import nl.hu.sd.inno.basicboot.shop.data.ProductRepository;
import nl.hu.sd.inno.basicboot.shop.domain.Order;
import nl.hu.sd.inno.basicboot.shop.domain.Person;
import nl.hu.sd.inno.basicboot.shop.domain.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orders;
    private final ProductRepository products;
    private final PersonRepository persons;

    public OrderService(OrderRepository orders, ProductRepository products, PersonRepository persons) {
        this.orders = orders;
        this.products = products;
        this.persons = persons;
    }

    public Optional<Person> findPerson(String name) {
        return this.persons.findByName(name);
    }

    public Optional<Product> findProduct(Long id) {
        return this.products.findById(id);
    }

    public List<Order> findOrders() {
        return this.orders.findAll();
    }

    public List<Order> findOrdersByPerson(Person p) {
        return this.orders.findByPerson(p);
    }

    public Order placeOrder(Person p, Map<Product, Integer> orderedProducts) {
        Order o = new Order(p);
        for (Product product : orderedProducts.keySet()) {
            o.addProduct(product, orderedProducts.get(product));
        }

        this.orders.save(o);
        o.process();

        return o;
    }

    public Optional<Order> findOrder(Long id) {
        return this.orders.findById(id);
    }
}
