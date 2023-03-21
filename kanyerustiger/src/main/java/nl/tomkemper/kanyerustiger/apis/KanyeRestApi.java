package nl.tomkemper.kanyerustiger.apis;

import nl.tomkemper.kanyerustiger.domain.Quote;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
public class KanyeRestApi implements QuoteApi {

    public record KanyeQuoteDTO(String quote){}

    @Override
    public Quote fetchQuote() {
        RestTemplate rt = new RestTemplate();

        KanyeQuoteDTO dto = rt.getForEntity("https://api.kanye.rest", KanyeQuoteDTO.class).getBody();

        return new Quote(dto.quote());
    }
}
