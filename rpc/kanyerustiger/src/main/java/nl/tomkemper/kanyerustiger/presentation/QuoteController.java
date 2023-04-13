package nl.tomkemper.kanyerustiger.presentation;

import nl.tomkemper.kanyerustiger.application.QuoteService;
import nl.tomkemper.kanyerustiger.domain.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quote")
public class QuoteController {

    private final QuoteService service;

    public QuoteController(QuoteService service){
        this.service = service;
    }

    public record QuoteDTO(String quote) {
    }

    @GetMapping
    public ResponseEntity<QuoteDTO> getQuote() {
        Quote q = this.service.getQuote();
        return ResponseEntity.ok(new QuoteDTO(q.getQuote()));
    }

}
