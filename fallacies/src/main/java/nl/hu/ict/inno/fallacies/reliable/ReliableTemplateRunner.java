package nl.hu.ict.inno.fallacies.reliable;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("NetworkIsReliable")
public class ReliableTemplateRunner implements CommandLineRunner {

    private RestTemplate template;

    public ReliableTemplateRunner(RestTemplate template){
        this.template = template;
    }

    @Override
    public void run(String... args) throws Exception {

        for(int i=0; i<100; i++){
            System.out.println("Call " + i);
            try{
                template.getForEntity("http://localhost:8080/test", String.class);
            }catch (HttpServerErrorException serverError){
                System.out.println("De server wou niet");
            }catch (Exception ex){
                System.out.println("De client wou niet");
            }

        }
    }
}
