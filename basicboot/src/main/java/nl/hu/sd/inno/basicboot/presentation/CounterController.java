package nl.hu.sd.inno.basicboot.presentation;

import nl.hu.sd.inno.basicboot.domain.Counter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;


@RestController
public class CounterController {

    private static Counter counter = new Counter();

    public record CounterDTO(Long id, int value) {
        public static CounterDTO fromCounter(Counter c) {
            return new CounterDTO(c.getId(), c.getCurrentValue());
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
