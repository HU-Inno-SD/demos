package nl.tomkemper.kanyerustiger.application;

import nl.tomkemper.kanyerustiger.apis.QuoteApi;
import nl.tomkemper.kanyerustiger.domain.Quote;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final QuoteApi api;

    public QuoteService(QuoteApi api){
        this.api = api;
    }

    public Quote getQuote(){
        return api.fetchQuote();
    }
}
