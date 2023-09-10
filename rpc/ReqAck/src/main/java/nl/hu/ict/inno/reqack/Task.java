package nl.hu.ict.inno.reqack;

public class Task {

    private Long id;

    private TaskStatus status = TaskStatus.Created;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
