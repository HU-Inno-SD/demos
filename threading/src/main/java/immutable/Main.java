package immutable;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CounterValue c = new CounterValue(0);

        int nrOfThreads = 10;
        int nrOfCountsPerThread = 100;

        ArrayList<Thread> threads = new ArrayList<>();

        CounterValue[] results = new CounterValue[nrOfThreads];

        for (int i = 0; i < nrOfThreads; i++) {
            int index = i;
            Thread t = new Thread(() -> {
                CounterValue local = c;
                for (int j = 0; j < nrOfCountsPerThread; j++) {
                    local = local.increment();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                results[index] = local;
            });
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            t.join();
        }

        CounterValue outcome = c.clone();
        for(CounterValue result: results){
            outcome = outcome.add(result);
        }

        System.out.println(outcome.getCount());
    }
}
