package nl.hu.ict.inno.fallacies.slow;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("NetworkIsSlow")
public class SlowTemplateRunner implements CommandLineRunner {

    private final RestTemplate template;

    public SlowTemplateRunner(RestTemplate template) {
        this.template = template;
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 100; i++) {
            System.out.println("Start Get " + i);
            template.getForEntity("http://localhost:8080/test", String.class);
            System.out.println("End Get " + i);

        }
    }
}
