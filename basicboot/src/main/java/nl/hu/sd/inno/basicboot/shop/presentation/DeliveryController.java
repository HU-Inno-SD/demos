package nl.hu.sd.inno.basicboot.shop.presentation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import nl.hu.sd.inno.basicboot.shop.application.DeliveryService;
import nl.hu.sd.inno.basicboot.shop.domain.Delivery;
import nl.hu.sd.inno.basicboot.shop.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public record DeliveryDto(long deliveryId, LocalDate deliveryDate,
                              long productId, String productName, int nrDelivered) {
        public static DeliveryDto fromDelivery(Delivery d) {
            return new DeliveryDto(d.getId(), d.getDeliveryDate(), d.getProduct().getId(), d.getProduct().getName(), d.getNrDelivered());
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record NewDeliveryDto(long productId, int nrDelivered) {
    }

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("")
    public ResponseEntity<List<DeliveryDto>> getDeliveries() {
        List<DeliveryDto> results = this.deliveryService.getDeliveries().stream().map(DeliveryDto::fromDelivery).toList();
        return ResponseEntity.ok(results);
    }

    @GetMapping("{id}")
    public ResponseEntity<DeliveryDto> getDelivery(@PathVariable("id") Long id) {
        Optional<Delivery> foundDelivery = this.deliveryService.getDeliveryById(id);
        if (foundDelivery.isPresent()) {
            return ResponseEntity.ok(DeliveryDto.fromDelivery(foundDelivery.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity newDelivery(@RequestBody NewDeliveryDto newDelivery) throws URISyntaxException {
        Optional<Product> foundProduct = this.deliveryService.getProductById(newDelivery.productId());
        if (foundProduct.isEmpty()) {
            return ResponseEntity.badRequest().body("ProductId %s not found".formatted(newDelivery.productId()));
        }

        Delivery freshDelivery = new Delivery(foundProduct.get(), newDelivery.nrDelivered);
        this.deliveryService.processDelivery(freshDelivery);

        return ResponseEntity.created(new URI("/deliveries/%s".formatted(freshDelivery.getId()))).build();
    }
}
