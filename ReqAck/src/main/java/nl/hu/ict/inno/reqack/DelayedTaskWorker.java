package nl.hu.ict.inno.reqack;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class DelayedTaskWorker implements TaskWorker {
    @Override
    public void perform(Task t) {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                t.setStatus(TaskStatus.Starting);
                Thread.sleep(2000);
                t.setStatus(TaskStatus.InProgress);
                Thread.sleep(6000);
                t.setStatus(TaskStatus.Completed); //Wat is hier niet DDD aan?:)
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
