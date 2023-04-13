package nl.hu.ict.inno.fallacies.cost;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("TransportCostZero")
public class CostTemplateRunner implements CommandLineRunner {

    private final RestTemplate template;
    private final CostFallacyFilter filter;

    public CostTemplateRunner(RestTemplate template, CostFallacyFilter filter) {

        this.template = template;
        this.filter = filter;
    }

    @Override
    public void run(String... args) throws Exception {
        String hi = "H";
        for (int i = 0; i < 100; i++) {
            System.out.println("Start Get " + i);
            template.getForEntity("http://localhost:8080/test?size="+i, String.class);
            System.out.println("End Get " + i);

            System.out.println("Start Post " + i);
            template.postForEntity("http://localhost:8080/test?size="+i, hi, String.class);
            System.out.println("End Post " + i);
            hi = hi + "i";
        }

        System.out.println(filter.getIncomingRequests());
        System.out.println(filter.getOutgoingRequests());
    }
}
