package nl.hu.sd.inno.basicboot.shop.application;

import nl.hu.sd.inno.basicboot.shop.data.DeliveryRepository;
import nl.hu.sd.inno.basicboot.shop.data.ProductRepository;
import nl.hu.sd.inno.basicboot.shop.domain.Delivery;
import nl.hu.sd.inno.basicboot.shop.domain.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//Tsja, wat voor Delivery is dit? Van onze winkel naar onze klant? Of vanuit onze leveranciers naar ons?
//
//Dit soort dingen zijn verwarrend in een nieuwe code-base, maar elk bedrijf bouwt vrij snel z'n eigen 'dialect' op.
//Zou zouden we bijv. het woord Delivery voor leveringen aan ons kunnen gebruiken, maar Shipment voor een levering aan
//een klant? Kan, en is arbitrair. Dit soort termen worden uiteindelijk het 'domein' waar we in week 6 & 7 verder op in
//gaan.
@Service
@Transactional
public class DeliveryService {
    private final DeliveryRepository deliveries;
    private final ProductRepository products;

    public DeliveryService(DeliveryRepository deliveries, ProductRepository products) {
        this.deliveries = deliveries;
        this.products = products;
    }

    public List<Delivery> getDeliveries() {
        return this.deliveries.findAll();
    }

    public Optional<Delivery> getDeliveryById(Long id) {
        return this.deliveries.findById(id);
    }

    public Optional<Product> getProductById(Long id){
        return this.products.findById(id);
    }

    public void processDelivery(Delivery newDelivery) {
        newDelivery.process();
        this.deliveries.save(newDelivery);
        //this.products.save(newDelivery.getProduct()); Let op: dit staat er niet, zou dit nodig zijn? Waarom/wanneer wel/niet?
    }

}
