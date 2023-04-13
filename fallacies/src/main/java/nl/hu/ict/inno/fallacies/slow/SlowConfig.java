package nl.hu.ict.inno.fallacies.slow;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
@Profile("NetworkIsSlow")
public class SlowConfig {
    @Bean
    public RestTemplate slowClient(SlowFallacyFilter filter) {
        RestTemplate rt = new RestTemplate();
        rt.setInterceptors(List.of(filter));
        return rt;
    }
}
