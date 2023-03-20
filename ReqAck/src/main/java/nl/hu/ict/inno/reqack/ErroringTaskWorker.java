package nl.hu.ict.inno.reqack;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
public class ErroringTaskWorker implements TaskWorker{
    @Override
    public void perform(Task t) {
        new Thread(()-> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            throw new Error("Dit vindt .NET echt niet leuk"); //Dit zou in .NET je hele server-proces doen crashen. Java kan er nog best aardig mee dealen eigenlijk!
        }).start();
    }
}
