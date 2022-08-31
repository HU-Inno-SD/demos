package nl.hu.sd.inno.basicboot.shop.data;

import nl.hu.sd.inno.basicboot.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
