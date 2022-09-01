package nl.hu.sd.inno.basicboot.shop.data;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProductRepositoryConfiguration {

    @Bean
    @Primary
    public ProductRepository createProductRepository(@Qualifier("productRepository") ProductRepository jpaGeneratedProductRepo){
        ProductRepository repo = jpaGeneratedProductRepo;
        repo = new SlowProductRepository(repo);
        repo = new CachingProductRepository(repo);

        return repo;
    }
}
