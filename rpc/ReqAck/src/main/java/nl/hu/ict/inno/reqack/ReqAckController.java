package nl.hu.ict.inno.reqack;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("longtasks")
@RestController
public class ReqAckController {

    private final TaskWorker worker;
    private final TaskRepository tasks;

    public ReqAckController(TaskWorker worker, TaskRepository tasks){
        this.worker = worker;
        this.tasks = tasks;
    }

    @GetMapping()
    public List<Task> all(){
        return this.tasks.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        Task t = this.tasks.getTask(id);
        if(t == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(t);
        }
    }



    @PostMapping()
    public ResponseEntity<Task> startTask(){
        Task t = new Task();
        tasks.add(t);
        worker.perform(t);

        return ResponseEntity
                .accepted()
                .location(URI.create("/longtasks/" + t.getId()))
                .body(t);
    }
}
