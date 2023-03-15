package nl.tomkemper.usessoap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientRunner implements CommandLineRunner {


    private final CountryClient client;

    public ClientRunner(CountryClient client){
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(1000); //lelijke hack om even te wachten tot de webservice wakker is:)

        var resp = this.client.getCountry("Nederland");
        System.out.println(resp.getCountry().getName());
    }
}
