package nl.hu.ict.inno.fallacies.reliable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@Profile("NetworkIsReliable")
public class ReliableConfig {
    @Bean
    public RestTemplate unreliableClient(ReliableFallacyFilter filter) {
        RestTemplate rt = new RestTemplate();
        rt.setInterceptors(List.of(filter));
        return rt;
    }
}
