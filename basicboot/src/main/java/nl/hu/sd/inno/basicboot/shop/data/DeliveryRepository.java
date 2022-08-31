package nl.hu.sd.inno.basicboot.shop.data;

import nl.hu.sd.inno.basicboot.shop.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
