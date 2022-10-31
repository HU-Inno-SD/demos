package nl.tomkemper.bep3.whutsupp.forwarding;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("forwarding")
public class ForwardingController {

    private final RemoteForwardingRepository repo;

    public ForwardingController(RemoteForwardingRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<List<ForwardingDTO>> getForwardingConfig() {
        Iterable<RemoteForwarding> forwardings = this.repo.findAll();
        List<ForwardingDTO> result = StreamSupport.stream(forwardings.spliterator(), false)
                .map(ForwardingDTO::new).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("{studentnr}")
    public ResponseEntity<ForwardingDTO> getForwardingConfig(@PathVariable long studentnr) {
        Optional<RemoteForwarding> forwarding = this.repo.findById(studentnr);
        if (forwarding.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ForwardingDTO(forwarding.get()));
    }

    @PutMapping("{studentnr}")
    @Transactional
    public ResponseEntity<ForwardingDTO> updateConfig(@PathVariable long studentnr, @RequestBody ForwardingDTO dto) {
        dto.studentNr = studentnr; //url is leading...
        Optional<RemoteForwarding> forwarding = this.repo.findById(studentnr);
        if (forwarding.isEmpty()) {
            forwarding = Optional.of(dto.toNewForwarding());
        }

        forwarding.get().update(dto);
        this.repo.save(forwarding.get());

        return ResponseEntity.ok(new ForwardingDTO(forwarding.get()));
    }

    @DeleteMapping("{studentnr}")
    @Transactional
    public ResponseEntity<ForwardingDTO> removeConfig(@PathVariable long studentnr) {
        Optional<RemoteForwarding> forwarding = this.repo.findById(studentnr);
        if (forwarding.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        this.repo.delete(forwarding.get());
        return ResponseEntity.noContent().build();
    }
}
