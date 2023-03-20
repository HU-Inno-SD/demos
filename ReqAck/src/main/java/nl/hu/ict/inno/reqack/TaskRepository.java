package nl.hu.ict.inno.reqack;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class TaskRepository {

    private HashMap<Long, Task> tasks = new HashMap<>();
    private long maxId = 1;

    public void add(Task t){
        t.setId(maxId);
        maxId++;

        tasks.put(t.getId(), t);
    }

    public Task getTask(Long id){
        return tasks.get(id);
    }

    public List<Task> getAll(){
        return this.tasks.values().stream().toList();
    }
}
