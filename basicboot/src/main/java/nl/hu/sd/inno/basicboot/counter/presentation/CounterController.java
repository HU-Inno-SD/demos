package nl.hu.sd.inno.basicboot.counter.presentation;

import nl.hu.sd.inno.basicboot.counter.domain.Counter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CounterController {

    private final Counter counter = Counter.getInstance();

    public record CounterDTO(int value) {
        public static CounterDTO fromCounter(Counter c) {
            return new CounterDTO(c.getCurrentValue());
        }
    }

    @GetMapping("/counter")
    public ResponseEntity<CounterDTO> getCounter() {
        return ResponseEntity.ok(CounterDTO.fromCounter(this.counter));
    }

    @PostMapping("/counter/increment")
    public ResponseEntity<CounterDTO> incrementCounter() {
        this.counter.increment();

        return ResponseEntity.ok(CounterDTO.fromCounter(this.counter));
    }
}
