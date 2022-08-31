package nl.hu.sd.inno.basicboot.shop.presentation;

import nl.hu.sd.inno.basicboot.shop.application.OrderService;
import nl.hu.sd.inno.basicboot.shop.domain.Order;
import nl.hu.sd.inno.basicboot.shop.domain.Person;
import nl.hu.sd.inno.basicboot.shop.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    public record OrderDto(Long id, LocalDate orderDate, String name) {
        public static OrderDto fromOrder(Order o) {
            return new OrderDto(o.getId(), o.getDate(), o.getPerson().getName());
        }
    }

    public record OrderLineDto(Long productId, String productName, int nr) {
    }

    public record OrderDetailsDto(Long id, LocalDate orderDate, String name, List<OrderLineDto> lines) {
        public static OrderDetailsDto fromOrder(Order o) {
            List<OrderLineDto> lines = o.getOrderLines().stream().map(ol -> new OrderLineDto(ol.getProduct().getId(), ol.getProduct().getName(), ol.getNr())).toList();
            return new OrderDetailsDto(o.getId(), o.getDate(), o.getPerson().getName(), lines);
        }
    }

    public record NewOrderDto(String name, List<OrderLineDto> lines) {
    }

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public ResponseEntity<List<OrderDto>> getOrders(@RequestParam(name = "name", required = false) String name) { //TODO: met een soort 'nep-huidige-gebruiker'
        List<Order> foundOrders;

        if (name == null) {
            foundOrders = this.orderService.findOrders();
        } else {
            Optional<Person> p = this.orderService.findPerson(name);
            if (p.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            foundOrders = this.orderService.findOrdersByPerson(p.get());
        }

        List<OrderDto> dtos = foundOrders.stream().map(OrderDto::fromOrder).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDetailsDto> getOrderDetails(@PathVariable Long id) {
        Optional<Order> foundOrder = this.orderService.findOrder(id);
        if (foundOrder.isPresent()) {
            return ResponseEntity.ok(OrderDetailsDto.fromOrder(foundOrder.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity newOrder(@RequestBody NewOrderDto newOrder) {
        Map<Product, Integer> productsOrdered = new HashMap<>();
        Optional<Person> personOrdering = this.orderService.findPerson(newOrder.name());
        if(personOrdering.isEmpty()){
            return ResponseEntity.badRequest().body("Person %s does not exist".formatted(newOrder.name()));
        }

        for(OrderLineDto line: newOrder.lines()){
            Optional<Product> foundProduct = this.orderService.findProduct(line.productId());
            if(foundProduct.isEmpty()){
                return ResponseEntity.badRequest().body("Product %s does not exist".formatted(line.productId()));
            }
            if(line.nr <= 0){
                return ResponseEntity.badRequest().body("Product %s must me ordered in amounts >0".formatted(line.productId()));
            }

            productsOrdered.put(foundProduct.get(), line.nr);
        }

        Order resultingOrder = this.orderService.placeOrder(personOrdering.get(), productsOrdered);
        return ResponseEntity.ok(OrderDetailsDto.fromOrder(resultingOrder));
    }

}
