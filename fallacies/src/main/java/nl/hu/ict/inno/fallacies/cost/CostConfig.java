package nl.hu.ict.inno.fallacies.cost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@Profile("TransportCostZero")
public class CostConfig {
    @Bean
    public RestTemplate slowClient(CostFallacyFilter filter) {
        RestTemplate rt = new RestTemplate();
        rt.setInterceptors(List.of(filter));
        return rt;
    }
}
